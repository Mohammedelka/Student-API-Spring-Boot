package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class studentService {
    private final studentRepository studentrepository;

    @Autowired
    public studentService(studentRepository studentrepository) {
        this.studentrepository = studentrepository;
    }

    public List<student> getstudents(){
        return studentrepository.findAll();
    }

    public void addNewStudent(student student) {
        Optional<student>  studentByEmail= studentrepository.findstudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentrepository.save(student);
    }

    public void deleteStudent(Long studentId) {
         boolean exists = studentrepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + "does not exists");
        }
        studentrepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        student student = studentrepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + "does not exist"
                ));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<student> studentOptional = studentrepository.findstudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("emial taken");
            }
            student.setEmail(email);
        }
    }
}
