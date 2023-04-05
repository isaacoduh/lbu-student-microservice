package com.example.studentmcs.service;

import com.example.studentmcs.dto.mapper;
import com.example.studentmcs.dto.responseDto.CourseResponseDto;
import com.example.studentmcs.model.Course;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService implements ICourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Course> searchCourses(String query) {
        List<Course> courses = courseRepository.searchCourses(query);
        return courses;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new IllegalArgumentException("course with id: " + courseId + " could not be found"));
        return course;
    }

//    @Override
//    public CourseResponseDto addStudentToCourse(Long courseId, Long studentId) {
//        Course course = getCourse(courseId);
//        Student student = studentService.getStudent(studentId);
//        if(student.getCourses().contains(student)){
//            throw new IllegalArgumentException("this student is already enrolled in this course");
//        }
//        course.addStudent(student);
//        student.addCourse(course);
//        courseRepository.save(course);
//        return mapper.courseToCourseResponseDto(course);
//    }

//    @Override
//    public CourseResponseDto removeStudentFromCourse(Long courseId, Long studentId) {
//        Course course = getCourse(courseId);
//        Student student = studentService.getStudent(studentId);
//        if(!(student.getCourses().contains(course))){
//            throw new IllegalArgumentException("course does not have this student enrolled!");
//        }
//        student.removeCourse(course);
//        course.deleteStudent(student);
//        return mapper.courseToCourseResponseDto(course);
//    }
}
