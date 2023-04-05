package com.example.studentmcs.model;

import com.example.studentmcs.dto.StaffDto;
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
public class Staff {
    @Id
    @SequenceGenerator(name = "seq_staff", sequenceName = "seq_staff", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_staff")
    private Long id;

    private String name;

    private String email;
    private String gender;
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "staff_project",
            joinColumns = {@JoinColumn(name = "staff_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "project_id", referencedColumnName = "id")}
    )
    private List<Project> projects = new ArrayList<>();

    public static Staff from (StaffDto staffDto){
        return Staff
                .builder()
                .id(staffDto.getId())
                .name(staffDto.getName())
                .email(staffDto.getEmail())
                .gender(staffDto.getGender())
                .isActive(staffDto.getIsActive())
                .projects(new ArrayList<>())
                .build();
    }

    public void addProjectToStaff(Project project)
    {
        this.getProjects().add(project);
    }

    public void removeProjectFromStaff(Project project){
        this.getProjects().remove(project);
    }
}
