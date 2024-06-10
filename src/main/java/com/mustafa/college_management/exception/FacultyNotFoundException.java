package com.mustafa.college_management.exception;

public class FacultyNotFoundException extends RuntimeException{
    public FacultyNotFoundException(String message){
        super(message);
    }
}
