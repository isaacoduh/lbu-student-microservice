package com.example.studentmcs.service;

import com.example.studentmcs.dto.responseDto.CourseResponseDto;
import com.example.studentmcs.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourseService {
    public List<CourseResponseDto> getAllCourses();
    public List<Course> searchCourses(String query);
    public Course getCourse(Long courseId);
    public CourseResponseDto addStudentToCourse(Long courseId, Long studentId);
    public CourseResponseDto removeStudentFromCourse(Long courseId, Long studentId);
}
