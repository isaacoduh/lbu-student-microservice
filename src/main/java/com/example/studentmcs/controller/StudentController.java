package com.example.studentmcs.controller;

import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.service.IStudentService;
import com.example.studentmcs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final IStudentService studentService;


    @GetMapping("/basic")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<String> helloStudent(){
        return ResponseEntity.ok("Hello from Secure Student Endpoint");
    }

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

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        List<StudentResponseDto> studentResponseDtos = studentService.getAllStudents();
        return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable final Long id)
    {
        Student student = studentService.viewStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<Student> updateStudentProfileById(@RequestBody final StudentRequestDto studentRequestDto, @PathVariable final Long id){
        Student student = studentService.updateStudentProfile(id, studentRequestDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
