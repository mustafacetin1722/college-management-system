package com.mustafa.college_management.service.impl;

import com.mustafa.college_management.dto.DepartmentDto;
import com.mustafa.college_management.exception.DepartmentNotFoundException;
import com.mustafa.college_management.exception.FacultyNotFoundException;
import com.mustafa.college_management.model.Department;
import com.mustafa.college_management.model.Faculty;
import com.mustafa.college_management.repository.DepartmentRepository;
import com.mustafa.college_management.repository.FacultyRepository;
import com.mustafa.college_management.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }
    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream()
                .map(department -> DepartmentDto.builder()
                        .name(department.getName())
                        .facultyId(String.valueOf(department.getFaculty().getId()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(x -> DepartmentDto.builder()
                .name(x.getName())
                .facultyId(String.valueOf(x.getFaculty().getId()))
                .build())
                .orElseThrow(() -> new DepartmentNotFoundException("There is no department present with this id: " + id));
    }

    @Override
    public String addDepartment(DepartmentDto departmentDto) {
        Optional<Faculty> faculty = facultyRepository.findById(Long.valueOf(departmentDto.getFacultyId()));
        if (!faculty.isPresent()){
            throw new FacultyNotFoundException("There is no faculty present with this id: " + departmentDto.getFacultyId());
        }
        Department department = Department.builder()
                .name(departmentDto.getName())
                .faculty(faculty.get())
                .uuid(UUID.randomUUID().toString())
                .build();
        departmentRepository.save(department);
        return "Department created, id: " + department.getId();
    }

    @Override
    public String updateDepartment(Long id, DepartmentDto departmentDto) {
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()){
            throw new DepartmentNotFoundException("There is no department present with this id: " + id);
        }
        department.get().setName(departmentDto.getName());
        departmentRepository.save(department.get());
        return "Department updated with this ID: " + id;
    }

    @Override
    public String deleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()){
            throw new DepartmentNotFoundException("There is no department present with this id: " + id);
        }
        departmentRepository.delete(department.get());
        return "Department has been deleted with this ID: " + id;
    }
}
