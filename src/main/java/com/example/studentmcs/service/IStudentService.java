package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.ProfileUpdateDto;
import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public interface IStudentService {
    public Student registerStudent(StudentRequestDto studentRequestDto);
    public List<StudentResponseDto> getAllStudents();
    public Student viewStudent(Long studentId);
    public Student getStudent(Long studentId);
    public Student updateStudentProfile(Long studentId, StudentRequestDto studentRequestDto);

    public Student updateStudentProfileAuth(String email, ProfileUpdateDto profileUpdateDto);

    public Student enrollInCourse(String email, Long courseId);

    public Student getStudentEnrollments(String email);
    Optional<Student> getStudentByUsername(String username);

    boolean hasStudentWithUsername(String username);

    boolean hasStudentWithEmail(String email);

    Student validateAndGetStudentByUsername(String username);

    Student saveStudent(Student student);

    Mono<Boolean> checkGraduation(String studentId);

    Boolean getGraduationStatus(String studentId);

}
