package com.example.studentmcs.service;

import com.example.studentmcs.model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStaffService {
    List<Staff> getAllStaff();
    Staff getStaffById(Long id);
    Staff saveStaff(Staff staff);
    Staff updateStaff(Staff staff);
    Staff deleteStaff(Long id);



    Staff addProjectToStaff(Long staffId, Long projectId);
    Staff removeProjectFromStaff(Long staffId, Long projectId);
}
