package com.mustafa.college_management.exception;

public class InstructorNotFoundException extends RuntimeException{
    public InstructorNotFoundException(String message){
        super(message);
    }
}
