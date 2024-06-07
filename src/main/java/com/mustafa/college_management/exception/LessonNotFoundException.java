package com.mustafa.college_management.exception;

public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(String message){
        super(message);
    }
}
