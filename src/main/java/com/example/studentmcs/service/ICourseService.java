package com.example.studentmcs.service;

import com.example.studentmcs.dto.responseDto.CourseResponseDto;
import com.example.studentmcs.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourseService {
    public List<Course> searchCourses(String query);
    List<Course> getAllCourse();
    public Course getCourse(Long courseId);
}
