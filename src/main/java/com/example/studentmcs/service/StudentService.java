package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student viewStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("student with studentId: " + studentId + " could not be found!"));
    }

    @Override
    public Student getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("student with id: " + studentId + " could not be found")
        );
        return student;
    }

    @Transactional
    @Override
    public Student updateStudentProfile(Long studentId, StudentRequestDto studentRequestDto) {
        Student studentProfileToUpdate = viewStudent(studentId);
        studentProfileToUpdate.setEmail(studentRequestDto.getEmail());
        studentProfileToUpdate.setFirstName(studentRequestDto.getFirstName());
        studentProfileToUpdate.setLastName(studentRequestDto.getLastName());
        return studentRepository.save(studentProfileToUpdate);
    }


}
