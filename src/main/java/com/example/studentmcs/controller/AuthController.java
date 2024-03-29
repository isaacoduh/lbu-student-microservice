package com.example.studentmcs.controller;

import com.example.studentmcs.dto.AuthResponse;
import com.example.studentmcs.dto.AuthenticationResponse;
import com.example.studentmcs.dto.StudentPlainDto;
import com.example.studentmcs.dto.requestDto.LoginRequest;
import com.example.studentmcs.dto.requestDto.SignUpRequest;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.security.CustomUserDetails;
import com.example.studentmcs.security.TokenProvider;
//import com.example.studentmcs.security.WebSecurityConfig;
import com.example.studentmcs.service.AuthenticationService;
import com.example.studentmcs.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createAccount(
            @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.createAccount(signUpRequest));
    };


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

}
