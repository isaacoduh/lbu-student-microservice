package com.example.studentmcs.service;

import com.example.studentmcs.model.Project;

import java.util.List;

public interface IProjectService {
    List<Project> getAllProject();
    Project getProjectById(Long id);
    Project saveProject(Project project);
    Project updateProject(Project project);
    Project deleteProject(Long id);
}
