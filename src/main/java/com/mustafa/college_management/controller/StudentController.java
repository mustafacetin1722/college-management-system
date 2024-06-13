package com.mustafa.college_management.controller;

import com.mustafa.college_management.dto.StudentDto;
import com.mustafa.college_management.dto.UserDto;
import com.mustafa.college_management.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.addStudent(studentDto),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateStudent(id,studentDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.deleteStudent(id),HttpStatus.OK);
    }
}
