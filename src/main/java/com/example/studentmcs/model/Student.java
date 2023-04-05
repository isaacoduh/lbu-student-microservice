package com.example.studentmcs.model;

import com.example.studentmcs.dto.StudentDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_students")
public class Student implements UserDetails {
    @Id
    @SequenceGenerator(name = "seq_student", sequenceName = "seq_student", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_student")
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")}
    )
    @ToString.Exclude
    private List<Course> courses  = new ArrayList<>();

    public static Student from (StudentDto studentDto){
        return Student
                .builder()
                .email(studentDto.getEmail())
                .courses(new ArrayList<>())
                .build();
    }

//    public  Student(String studentId,String firstName, String lastName, String email, String password)
//    {
//        this.studentId = studentId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//
//    }

    // save point

//    public void addCourse(Course course)
//    {
//
//        courses.add(course);
//    }

//    public void removeCourse(Course course)
//    {
//        courses.remove(course);
//    }
    public void addCourseToStudent(Course course) {this.getCourses().add(course);}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
