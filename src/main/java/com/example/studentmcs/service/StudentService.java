package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.ProfileUpdateDto;
import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.dto.mapper;

import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = StreamSupport.stream(
                studentRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
        return mapper.studentsToStudentResponseDtos(students);
    }

    @Override
    public Student viewStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("student with studentId: " + studentId + " could not be found!"));
    }

    public Student viewStudentByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("student with email: " + email + " could not be found!"));
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

    @Transactional
    @Override
    public Student updateStudentProfileAuth(String email, ProfileUpdateDto profileUpdateDto) {
        Student studentProfileToUpdate = viewStudentByEmail(email);
        System.out.print(profileUpdateDto.getFirstName());
        System.out.print(profileUpdateDto.getLastName());
        studentProfileToUpdate.setFirstName(profileUpdateDto.getFirstName());
        studentProfileToUpdate.setLastName(profileUpdateDto.getLastName());
        return studentRepository.save(studentProfileToUpdate);
    }

    @Override
    public Optional<Student> getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public boolean hasStudentWithUsername(String username) {
        return studentRepository.existsByUsername(username);
    }

    @Override
    public boolean hasStudentWithEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public Student validateAndGetStudentByUsername(String username) {
        return getStudentByUsername(username).orElseThrow(() -> new IllegalArgumentException(String.format("Student with username not found!")));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


}
