package com.example.studentmcs.controller;

import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.service.IStudentService;
import com.example.studentmcs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody final StudentRequestDto studentRequestDto)
    {
        Student student = studentService.registerStudent(studentRequestDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
