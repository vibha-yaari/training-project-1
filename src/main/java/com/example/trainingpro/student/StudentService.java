package com.example.trainingpro.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;


@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();

    }


    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail = studentRepo.findStudentbyEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Integer studentId) {
        boolean exists =studentRepo.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("student with id "+studentId+" doesn't exits");
        }
        studentRepo.deleteById(studentId);
    }


    @Transactional
    public void updatestudent(Integer studentId, String name, String email) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id "+studentId+" doesn't exits"));
        if (name != null && name.length()>0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepo.findStudentbyEmail(email);
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
