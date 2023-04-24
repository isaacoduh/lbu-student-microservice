package com.example.studentmcs.dto;

import com.example.studentmcs.model.Course;
import com.example.studentmcs.model.Student;
import jakarta.persistence.Column;
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
public class CourseDto {
    private Long id;
    private String courseId;

    private String courseTitle;
    private String courseTerm;

    private Double courseFee;

    private List<StudentPlainDto> studentList = new ArrayList<>();

    public static CourseDto from(Course course){
        List<Student> studentList = course.getStudentList();
        List<StudentPlainDto> studentPlainDtos = studentList.stream()
                .map(StudentPlainDto::from).toList();
        return CourseDto.builder()
                .id(course.getId())
                .courseId(course.getCourseId())
                .courseTitle(course.getCourseTitle())
                .courseTerm(course.getCourseTerm())
                .courseFee(course.getCourseFee())
                .studentList(studentPlainDtos)
                .build();
    }
}
