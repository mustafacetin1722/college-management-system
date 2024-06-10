package com.mustafa.college_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(StudentNotFoundException.class)
    public ProblemDetail studentNotFoundException(StudentNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setTitle("Student not found!!!");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ProblemDetail departmentNotFoundException(DepartmentNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setTitle("Department not found!!!");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(LessonNotFoundException.class)
    public ProblemDetail lessonNotFoundException(LessonNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setTitle("Lesson not found!!!");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(InstructorNotFoundException.class)
    public ProblemDetail instructorNotFoundException(InstructorNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setTitle("Instructor not found!!!");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(FacultyNotFoundException.class)
    public ProblemDetail facultyNotFoundException(FacultyNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setTitle("Faculty not found!!!");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
