package com.example.studentmcs.dto;

import com.example.studentmcs.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CoursePlainDto {
    private Long id;
    private String courseId;

    private String courseTitle;
    private String courseTerm;

    private Double courseFee;

    public static CoursePlainDto from (Course course){
        return CoursePlainDto.builder()
                .id(course.getId())
                .courseId(course.getCourseId())
                .courseTitle(course.getCourseTitle())
                .courseTerm(course.getCourseTerm())
                .courseFee(course.getCourseFee())
                .build();
    }

}
