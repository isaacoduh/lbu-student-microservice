package com.example.studentmcs.model;

import com.example.studentmcs.dto.CourseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_courses")
public class Course {
    @Id
    @SequenceGenerator(
            name = "seq_course",
            sequenceName = "seq_course",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course")
    private Long id;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "course_term")
    private String courseTerm;

    @Column(name = "course_fee")
    private Double courseFee;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "student_course",
//            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "course_id")}
//    )
    private List<Student> studentList = new ArrayList<>();

    public static Course from (CourseDto courseDto){
        return Course
                .builder()
                .id(courseDto.getId())
                .courseId(courseDto.getCourseId())
                .courseTitle(courseDto.getCourseTitle())
                .courseTerm(courseDto.getCourseTerm())
                .courseFee(courseDto.getCourseFee())
                .studentList(new ArrayList<>())
                .build();
    }



    public Course(String courseId, String courseTitle, String courseTerm, Double courseFee){
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseTerm = courseTerm;
        this.courseFee = courseFee;
    }
//
//    public void addStudent(Student student){
//        students.add(student);
//    }
//
//    public void deleteStudent(Student student){
//        students.remove(student);
//    }
}
