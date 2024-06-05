package com.mustafa.college_management.service;

import com.mustafa.college_management.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();

    StudentDto getById(Long id);

    String addStudent(StudentDto studentDto);

    String updateStudent(Long id, StudentDto studentDto);

    String deleteStudent(Long id);
}
