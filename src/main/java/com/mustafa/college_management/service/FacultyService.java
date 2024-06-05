package com.mustafa.college_management.service;

import com.mustafa.college_management.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    List<FacultyDto> getAll();

    FacultyDto getById(Long id);

    String addFaculty(FacultyDto facultyDto);

    String updateFaculty(Long id, FacultyDto facultyDto);

    String deleteFaculty(Long id);
}
