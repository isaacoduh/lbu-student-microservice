package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.model.Student;
import org.springframework.stereotype.Service;

@Service
public interface IStudentService {
    public Student registerStudent(StudentRequestDto studentRequestDto);
}
