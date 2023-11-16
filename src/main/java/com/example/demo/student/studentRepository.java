package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface studentRepository extends JpaRepository<student, Long> {

    // SELECT * FROM student WHERE email=?
    @Query("SELECT s FROM student s WHERE s.email=?1")
    Optional<student> findstudentByEmail(String email);
}
