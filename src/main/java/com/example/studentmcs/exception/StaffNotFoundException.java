package com.example.studentmcs.exception;

import java.text.MessageFormat;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException(Long id){
        super(MessageFormat.format("Staff {0} is Not found!", id));
    }
}
