package com.example.studentmcs.dto;

import com.example.studentmcs.dto.responseDto.CourseResponseDto;
import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Course;
import com.example.studentmcs.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class mapper {

//    public static CourseResponseDto courseToCourseResponseDto(Course course){
//        CourseResponseDto courseResponseDto = new CourseResponseDto();
//        courseResponseDto.setCourseId(course.getCourseId());
//        courseResponseDto.setCourseTitle(course.getCourseTitle());
//        courseResponseDto.setCourseTerm(course.getCourseTerm());
//
//        List<String> lNames = new ArrayList<>();
//        List<Student> students = course.getStudents();
//        for(Student student: students) {
//            lNames.add(student.getStudentId());
//        }
//        courseResponseDto.setStudentIds(lNames);
//        return courseResponseDto;
//    }

//    public static List<CourseResponseDto> coursesToCourseResponseDtos(List<Course> courses) {
//        List<CourseResponseDto> courseResponseDtos = new ArrayList<>();
//        for(Course course: courses){
//            courseResponseDtos.add(courseToCourseResponseDto(course));
//        }
//        return courseResponseDtos;
//    }
//    public static StudentResponseDto studentToStudentResponseDto(Student student)
//    {
//        StudentResponseDto studentResponseDto = new StudentResponseDto();
//        studentResponseDto.setStudentId(student.getStudentId());
//        studentResponseDto.setFirstName(student.getFirstName());
//        studentResponseDto.setLastName(student.getLastName());
//        studentResponseDto.setEmail(student.getEmail());
//        List<String> enrolledCourses = new ArrayList<>();
//        Set<Course> courses = student.getCourses();
//        for(Course course: courses) {
//            enrolledCourses.add(course.getCourseTitle());
//            enrolledCourses.add(course.getCourseId());
//        }
//        studentResponseDto.setEnrolledCourses(enrolledCourses);
//        return studentResponseDto;
//    }

//    public static List<StudentResponseDto> studentsToStudentResponseDtos(List<Student> students){
//        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
//        for(Student student: students){
//            studentResponseDtos.add(studentToStudentResponseDto(student));
//        }
//        return studentResponseDtos;
//    }
}
