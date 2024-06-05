package com.mustafa.college_management.service;

import com.mustafa.college_management.dto.InstructorDto;

import java.util.List;

public interface InstructorService {
    List<InstructorDto> getAll();

    InstructorDto getById(Long id);

    String addInstructor(InstructorDto instructorDto);

    String updateInstructor(Long id, InstructorDto instructorDto);

    String deleteInstructor(Long id);
}
