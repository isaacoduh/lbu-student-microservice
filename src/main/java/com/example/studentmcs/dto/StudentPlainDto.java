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
public class StudentPlainDto {

    private String username;

    public static StudentPlainDto from(Student student){
        return StudentPlainDto
                .builder()
                .username(student.getUsername())
                .build();
    }
}
