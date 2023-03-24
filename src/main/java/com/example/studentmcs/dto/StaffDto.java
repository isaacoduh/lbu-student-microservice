package com.example.studentmcs.dto;

import com.example.studentmcs.model.Project;
import com.example.studentmcs.model.Staff;
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
public class StaffDto {
    private Long id;
    private String name;
    private String email;

    private String gender;
    private Boolean isActive;

    private List<ProjectPlainDto> projects = new ArrayList<>();

    public static StaffDto from (Staff staff) {
        List<Project> projects = staff.getProjects();
        List<ProjectPlainDto> projectPlainDtos = projects
                .stream()
                .map(ProjectPlainDto::from)
                .toList();
        return StaffDto.builder()
                .id(staff.getId())
                .name(staff.getName())
                .email(staff.getEmail())
                .gender(staff.getGender())
                .isActive(staff.getIsActive())
                .build();
    }
}
