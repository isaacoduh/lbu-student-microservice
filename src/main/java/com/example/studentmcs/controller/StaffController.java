package com.example.studentmcs.controller;

import com.example.studentmcs.dto.StaffDto;
import com.example.studentmcs.model.Staff;
import com.example.studentmcs.service.IStaffService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    private final IStaffService staffService;

    @Autowired
    public StaffController(IStaffService staffService)
    {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff(){
        List<Staff> staffList = staffService.getAllStaff();
        List<StaffDto> staffDtos = staffList.stream().map(StaffDto::from).toList();
        return ResponseEntity.status(HttpStatus.OK).body(staffDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable("id") final Long id){
        Staff staff = staffService.getStaffById(id);
        StaffDto staffDto = StaffDto.from(staff);
        return ResponseEntity.status(HttpStatus.OK).body(staffDto);
    }

    @PostMapping
    public ResponseEntity<StaffDto> saveStaff(@RequestBody final StaffDto staffDto){
        Staff staff = staffService.saveStaff(Staff.from(staffDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffDto.from(staff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StaffDto> deleteStaff(@PathVariable("id") final Long id){
        Staff staff = staffService.deleteStaff(id);
        return ResponseEntity.status(HttpStatus.OK).body(StaffDto.from(staff));
    }

    @PostMapping("/{staffId}/projects/{projectId}")
    public ResponseEntity<StaffDto> addProjectToEmployee(
            @PathVariable("staffId") final Long staffId,
            @PathVariable("projectId") final Long projectId
    ){
        Staff staff = staffService.removeProjectFromStaff(staffId, projectId);
        return ResponseEntity.status(HttpStatus.OK).body(StaffDto.from(staff));
    }
}
