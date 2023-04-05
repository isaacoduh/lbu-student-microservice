package com.example.studentmcs.dto;

import com.example.studentmcs.model.Course;
import com.example.studentmcs.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDto {
    private String email;

    private List<CoursePlainDto> courses = new ArrayList<>();

    public static StudentDto from (Student student){
        List<Course> courses = student.getCourses();
        List<CoursePlainDto> coursePlainDtos = courses
                .stream()
                .map(CoursePlainDto::from)
                .toList();
        return StudentDto.builder()
                .email(student.getEmail())
                .courses(coursePlainDtos)
                .build();
    }
}
