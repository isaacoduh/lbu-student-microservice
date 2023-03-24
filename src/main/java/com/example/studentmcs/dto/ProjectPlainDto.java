package com.example.studentmcs.dto;

import com.example.studentmcs.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectPlainDto {
    private Long id;
    private String name;
    private String code;
    private Boolean isActive;
    public static ProjectPlainDto from(Project project){
        return ProjectPlainDto.builder()
                .id(project.getId())
                .name(project.getName())
                .code(project.getCode())
                .isActive(project.getIsActive())
                .build();
    }
}
