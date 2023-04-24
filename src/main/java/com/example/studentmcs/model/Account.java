package com.example.studentmcs.model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String studentId;

    private boolean hasOutstandingBalance;
}
