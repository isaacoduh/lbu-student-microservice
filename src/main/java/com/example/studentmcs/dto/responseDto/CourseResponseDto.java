package com.example.studentmcs.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponseDto {
    private String courseId;
    private List<String> studentIds;

}
