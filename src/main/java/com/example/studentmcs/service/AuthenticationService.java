package com.example.studentmcs.service;

import com.example.studentmcs.dto.AuthenticationResponse;
import com.example.studentmcs.dto.requestDto.LoginRequest;
import com.example.studentmcs.dto.requestDto.SignUpRequest;
import com.example.studentmcs.model.Account;
import com.example.studentmcs.model.Role;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

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

    @Autowired
    private final IntegrationService integrationService;

    public AuthenticationResponse createAccount(SignUpRequest signUpRequest){
        var student = Student.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .studentId(generateStudentId())
                .role(Role.STUDENT)
                .build();

        studentRepository.save(student);

        // generate a new account object
        Account account = new Account();
        account.setStudentId(student.getStudentId());
        account.setHasOutstandingBalance(false);
        System.out.print(account);

        integrationService.postAccountData(account);

        System.out.println(account);

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


    // TODO: Generate a unique student ID;

    private static String generateStudentId()
    {
        // Define the characters that can be used in the student ID
        String characterSet = "0123456789";

        // Create a StringBuilder to store the student ID
        StringBuilder studentId = new StringBuilder();

        // Add the letter 'c' to the beginning of the student ID
        studentId.append("c");


        Random random = new Random();

        for(int i = 0; i<7; i++) {
            int index = random.nextInt(characterSet.length());
            studentId.append(characterSet.charAt(index));
        }
        return studentId.toString();
    }
}
