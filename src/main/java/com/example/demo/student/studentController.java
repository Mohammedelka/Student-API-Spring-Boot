package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class studentController {
    private final studentService studentservice;
    @Autowired
    public studentController(studentService studentservice) {
        this.studentservice = studentservice;
    }


    @GetMapping
    public List<student> getstudents(){
       return studentservice.getstudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody student student){
        studentservice.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
           studentservice.deleteStudent(studentId);
    }

    @PutMapping(path= "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required= false) String name,
            @RequestParam(required= false) String email){

        studentservice.updateStudent(studentId, name, email);
    }

}
