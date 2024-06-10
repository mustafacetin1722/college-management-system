package com.mustafa.college_management.service.impl;

import com.mustafa.college_management.dto.FacultyDto;
import com.mustafa.college_management.exception.FacultyNotFoundException;
import com.mustafa.college_management.model.Faculty;
import com.mustafa.college_management.repository.FacultyRepository;
import com.mustafa.college_management.service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    @Override
    public List<FacultyDto> getAll() {
        return facultyRepository.findAll().stream()
                .map(faculty -> FacultyDto.builder()
                        .id(faculty.getId())
                        .name(faculty.getName())
                        .campus(faculty.getCampus())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public FacultyDto getById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        return faculty.map(x -> FacultyDto.builder()
                .name(faculty.get().getName())
                .campus(faculty.get().getCampus())
                .build())
                .orElseThrow(() -> new FacultyNotFoundException("There is no faculty present with this id: " + id));
    }

    @Override
    public String addFaculty(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        faculty.setUuid(UUID.randomUUID().toString());
        faculty.setCampus(facultyDto.getCampus());
        facultyRepository.save(faculty);
        return "Faculty created , id :  " + faculty.getId();
    }

    @Override
    public String updateFaculty(Long id, FacultyDto facultyDto) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (!faculty.isPresent()){
            throw new FacultyNotFoundException("There is no faculty present with this id: " + id);
        }
        faculty.get().setName(facultyDto.getName());
        faculty.get().setCampus(facultyDto.getCampus());
        facultyRepository.save(faculty.get());
        return "Faculty updated with this ID: " + id;
    }

    @Override
    public String deleteFaculty(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (!faculty.isPresent()){
            throw new FacultyNotFoundException("There is no faculty present with this id: " + id);
        }
        facultyRepository.delete(faculty.get());
        return "Faculty has been deleted with this ID: " + id;
    }
}
