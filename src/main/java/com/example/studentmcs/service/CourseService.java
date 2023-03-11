package com.example.studentmcs.service;

import com.example.studentmcs.model.Course;
import com.example.studentmcs.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> searchCourses(String query) {
        List<Course> courses = courseRepository.searchCourses(query);
        return courses;
    }
}
