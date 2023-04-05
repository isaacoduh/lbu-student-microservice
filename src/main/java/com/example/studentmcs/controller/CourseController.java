package com.example.studentmcs.controller;

import com.example.studentmcs.dto.CourseDto;
import com.example.studentmcs.dto.responseDto.CourseResponseDto;
import com.example.studentmcs.model.Course;
import com.example.studentmcs.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService)
    {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourse(){
        List<Course> courses = courseService.getAllCourse();
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::from).toList();
        return ResponseEntity.status(HttpStatus.OK).body(courseDtos);
    }

//    @GetMapping("/getAll")
//    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
//        List<CourseResponseDto> courses = courseService.getAllCourses();
//        return new ResponseEntity<>(courses, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam("query") String query){
        return ResponseEntity.ok(courseService.searchCourses(query));
    }

//    @PostMapping("/{courseId}/enrol/{studentId}")
//    public ResponseEntity<CourseResponseDto> addStudent(@PathVariable final Long studentId, @PathVariable Long courseId)
//    {
//        CourseResponseDto courseResponseDto = courseService.addStudentToCourse(courseId, studentId);
//        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
//    }


//    @PostMapping("/{courseId}/remove/{studentId}")
//    public ResponseEntity<CourseResponseDto> removeStudent(@PathVariable final Long studentId, @PathVariable final Long courseId)
//    {
//        CourseResponseDto courseResponseDto = courseService.removeStudentFromCourse(courseId, studentId);
//        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
//    }
}
