package com.example.trainingpro.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping

    public List <Student> getStu(){
        return studentService.getStudents();
    }

    @GetMapping(path=("{studentId}"))
    @Cacheable(value = "s", key = "#studentId")
    public Student getStudentById(@PathVariable("studentId") Integer studentId){
        return studentService.getStudentById(studentId);
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

        @DeleteMapping(path = "{studentId}")
    @CacheEvict(value = "student",allEntries = true)
    public void delStudent(@PathVariable("studentId") Integer studentId){
        studentService.deleteStudent(studentId);
}
    @PutMapping(path = "{studentId}")
    @CachePut(value = "student",key="#studentId")
    public void updatestudent(@PathVariable("studentId") Integer studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email
                              ){
        studentService.updatestudent(studentId,name,email);

}
}
