package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student registerStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setStudentId(studentRequestDto.getStudentId());
        student.setEmail(studentRequestDto.getEmail());
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        return studentRepository.save(student);
    }
}
