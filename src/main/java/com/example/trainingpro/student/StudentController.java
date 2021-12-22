package com.example.trainingpro.student;

import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

@DeleteMapping(path = "{studentId}")
    public void delStudent(@PathVariable("studentId") Integer studentId){
        studentService.deleteStudent(studentId);
}
@PutMapping(path = "{studentId}")
    public void updatestudent(@PathVariable("studentId") Integer studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email
                              ){
        studentService.updatestudent(studentId,name,email);

}
}
