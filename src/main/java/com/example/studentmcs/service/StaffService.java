package com.example.studentmcs.service;

import com.example.studentmcs.exception.StaffNotFoundException;
import com.example.studentmcs.model.Project;
import com.example.studentmcs.model.Staff;
import com.example.studentmcs.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService implements IStaffService{

    private final StaffRepository staffRepository;
    private final IProjectService projectService;

    @Autowired
    public StaffService(StaffRepository staffRepository, IProjectService projectService){
        this.staffRepository = staffRepository;
        this.projectService = projectService;
    }
    @Override
    public List<Staff> getAllStaff() {
        // TODO: implement get all by is active later
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException(id));
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return null;
    }

    @Override
    public Staff deleteStaff(Long id) {
        Staff staff = getStaffById(id);
        staff.setProjects(new ArrayList<>());
        staff.setIsActive(false);
        return staffRepository.save(staff);
    }

    @Override
    public Staff addProjectToStaff(Long staffId, Long projectId) {
       Staff staff = getStaffById(staffId);
        Project project = projectService.getProjectById(projectId);
        staff.addProjectToStaff(project);
        return staffRepository.save(staff);
    }

    @Override
    public Staff removeProjectFromStaff(Long staffId, Long projectId) {
        Staff staff = getStaffById(staffId);
        Project project = projectService.getProjectById(projectId);
        staff.removeProjectFromStaff(project);
        return staffRepository.save(staff);
    }
}
