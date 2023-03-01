package com.example.studentmcs.repository;

import com.example.studentmcs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
