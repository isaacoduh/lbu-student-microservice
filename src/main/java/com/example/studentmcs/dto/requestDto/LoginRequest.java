package com.example.studentmcs.dto.requestDto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;

    private String password;
}
