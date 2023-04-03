package com.example.studentmcs.controller;

import com.example.studentmcs.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello Ok");
    }

    @GetMapping("/cuser")
    public String currentUsername(@AuthenticationPrincipal Student student){
        return student.getEmail();
    }
}
