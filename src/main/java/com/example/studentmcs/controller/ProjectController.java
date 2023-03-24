package com.example.studentmcs.controller;

import com.example.studentmcs.dto.ProjectDto;
import com.example.studentmcs.model.Project;
import com.example.studentmcs.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProject(){
        List<Project> projects = projectService.getAllProject();
        List<ProjectDto> projectDtos = projects.stream().map(ProjectDto::from).toList();
        return ResponseEntity.status(HttpStatus.OK).body(projectDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") final Long id){
        Project project = projectService.getProjectById(id);
        ProjectDto projectDto = ProjectDto.from(project);
        return ResponseEntity.status(HttpStatus.OK).body(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveProject(@RequestBody final ProjectDto projectDto){
        Project project = projectService.saveProject(Project.from(projectDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProjectDto.from(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable final Long id){
        Project project = projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.OK).body(ProjectDto.from(project));
    }
}
