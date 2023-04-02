package com.example.studentmcs.service;

import com.example.studentmcs.dto.AuthenticationResponse;
import com.example.studentmcs.dto.requestDto.LoginRequest;
import com.example.studentmcs.dto.requestDto.SignUpRequest;
import com.example.studentmcs.model.Role;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final StudentRepository studentRepository;


    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse createAccount(SignUpRequest signUpRequest){
        var student = Student.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .studentId(signUpRequest.getStudentId())
                .role(Role.STUDENT)
                .build();

        studentRepository.save(student);

        var jwtToken = jwtService.generateTokenFromUserDetails(student);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        var student = studentRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateTokenFromUserDetails(student);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
