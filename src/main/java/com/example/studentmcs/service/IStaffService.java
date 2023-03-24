package com.example.studentmcs.service;

import com.example.studentmcs.model.Staff;

import java.util.List;

public interface IStaffService {
    List<Staff> getAllStaff();
    Staff getStaffById(Long id);
    Staff saveStaff(Staff staff);
    Staff updateStaff(Staff staff);
    Staff deleteStaff(Long id);

    Staff addProjectToStaff(Long staffId, Long projectId);
    Staff removeProjectFromStaff(Long staffId, Long projectId);
}
