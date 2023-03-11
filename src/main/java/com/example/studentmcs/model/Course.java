package com.example.studentmcs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "course_term")
    private String courseTerm;

    public Course(String courseId, String courseTitle, String courseTerm){
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseTerm = courseTerm;
    }
}
