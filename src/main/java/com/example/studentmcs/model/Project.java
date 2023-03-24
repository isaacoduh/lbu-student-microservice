package com.example.studentmcs.model;

import com.example.studentmcs.dto.ProjectDto;
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
public class Project {
    @Id
    @SequenceGenerator(
            name = "seq_project",
            sequenceName = "seq_project",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
    private Long id;
    private String name;
    private String code;
    private Boolean isActive;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    List<Staff> staffList = new ArrayList<>();

    public static Project from(ProjectDto projectDto){
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .code(projectDto.getCode())
                .isActive(projectDto.getIsActive())
                .staffList(new ArrayList<>())
                .build();
    }
}
