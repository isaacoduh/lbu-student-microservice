package com.example.studentmcs.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDto {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> enrolledCourses;
}
