package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {
    public Student registerStudent(StudentRequestDto studentRequestDto);
    public List<StudentResponseDto> getAllStudents();
    public Student viewStudent(Long studentId);
    public Student getStudent(Long studentId);
    public Student updateStudentProfile(Long studentId, StudentRequestDto studentRequestDto);
    // TODO: Add Graduation
}
