package com.example.studentmcs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email
    private String email;

    private String password;

    private String role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> courses;

    public  Student(String username, String studentId,String firstName, String lastName, String email, String password, String role)
    {
        this.username = username;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // save point

    public void addCourse(Course course)
    {

        courses.add(course);
    }

    public void removeCourse(Course course)
    {
        courses.remove(course);
    }

}
