package com.example.studentmcs.service;

import com.example.studentmcs.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourseService {
    public List<Course> getAllCourses();
    public List<Course> searchCourses(String query);
}
