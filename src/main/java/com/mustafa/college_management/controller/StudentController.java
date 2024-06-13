package com.mustafa.college_management.controller;

import com.mustafa.college_management.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

}
