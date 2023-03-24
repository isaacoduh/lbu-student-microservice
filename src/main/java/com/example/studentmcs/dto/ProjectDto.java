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
public class ProjectDto {
    private Long id;
    private String name;
    private String code;
    private Boolean isActive;

    private List<StaffPlainDto> staffList = new ArrayList<>();

    public static ProjectDto from(Project project)
    {
        List<Staff> staffList = project.getStaffList();
        List<StaffPlainDto> staffPlainDtos = staffList.stream()
                .map(StaffPlainDto::from).toList();
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .code(project.getCode())
                .isActive(project.getIsActive())
                .staffList(staffPlainDtos)
                .build();
    }
}
