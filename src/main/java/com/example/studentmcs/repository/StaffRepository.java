package com.example.studentmcs.repository;

import com.example.studentmcs.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}