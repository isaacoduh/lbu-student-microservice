package com.example.studentmcs.dto;

import com.example.studentmcs.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentProfileDto {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;

    public static StudentProfileDto from (Student student){
        return StudentProfileDto.builder()
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();
    }
}
