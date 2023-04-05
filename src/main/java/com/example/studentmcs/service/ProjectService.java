package com.example.studentmcs.service;

import com.example.studentmcs.exception.ProjectNotFoundException;
import com.example.studentmcs.model.Project;
import com.example.studentmcs.model.Staff;
import com.example.studentmcs.repository.ProjectRepository;
import com.example.studentmcs.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService{
    private final ProjectRepository projectRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, StaffRepository staffRepository){
        this.projectRepository = projectRepository;
        this.staffRepository = staffRepository;
    }
    @Override
    public List<Project> getAllProject() {
        // TODO: implement get by is active
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return null;
    }

//    @Override
//    public Project deleteProject(Long id) {
//        Project project = getProjectById(id);
//        project.setIsActive(false);
//        List<Staff> staffList = project.getStaffList();
//        for(Staff staff: staffList){
//            staff.getProjects().remove(project);
//            staffRepository.save(staff);
//        }
//        return projectRepository.save(project);
//    }
}
