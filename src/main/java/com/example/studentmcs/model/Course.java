package com.example.studentmcs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_enrolments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public Course(String courseId, String courseTitle, String courseTerm){
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseTerm = courseTerm;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent(Student student){
        students.remove(student);
    }
}
