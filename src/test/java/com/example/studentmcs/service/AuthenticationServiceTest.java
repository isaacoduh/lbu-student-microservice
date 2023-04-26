package com.example.studentmcs.service;

import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthenticationServiceTest {
    private final String email  = "ocho@mail.com";
    private final String studentId = "c34567800";
    private final  Long id = 1L;
//    private Student firstStudent;
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;
    @BeforeEach
    public void setUp()
    {
        Student student = new Student();
        student.setId(1L);
        student.setEmail(email);
        student.setStudentId(studentId);
        Mockito.when(studentRepository.findByEmail(email))
                .thenReturn(Optional.of(student));
        Mockito.when(studentRepository.existsByStudentId(studentId)).thenReturn(true);
    }

    @Test
    void testGetStudentWithEmailReturnsExistingStudent() {
        Student result = studentService.viewStudentByEmail(email);
        assertThat(email.equals(result.getEmail()));
    }

    @Test
    void testGetStudentWithBooleanValue()
    {
        boolean result = studentService.hasStudentWithEmail(email);
        assertThat(true);
    }
}
