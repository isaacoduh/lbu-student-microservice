package com.example.studentmcs.exception;

import java.text.MessageFormat;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id){
        super(MessageFormat.format("Project {0} is not found!",id));
    }
}
