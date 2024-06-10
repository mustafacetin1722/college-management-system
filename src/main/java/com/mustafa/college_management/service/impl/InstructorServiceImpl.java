package com.mustafa.college_management.service.impl;

import com.mustafa.college_management.dto.InstructorDto;
import com.mustafa.college_management.exception.DepartmentNotFoundException;
import com.mustafa.college_management.exception.InstructorNotFoundException;
import com.mustafa.college_management.model.*;
import com.mustafa.college_management.repository.DepartmentRepository;
import com.mustafa.college_management.repository.InstructorRepository;
import com.mustafa.college_management.repository.LessonRepository;
import com.mustafa.college_management.repository.UserRepository;
import com.mustafa.college_management.service.InstructorService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final DepartmentRepository departmentRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public InstructorServiceImpl(InstructorRepository instructorRepository,
                                 DepartmentRepository departmentRepository,
                                 LessonRepository lessonRepository, UserRepository userRepository,
                                 BCryptPasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.departmentRepository = departmentRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<InstructorDto> getAll() {
        return instructorRepository.findAll().stream().map(instructor -> InstructorDto.builder()
                .name(instructor.getName())
                .email(instructor.getEmail())
                .phone(instructor.getPhone())
                .build()).collect(Collectors.toList());
    }

    @Override
    public InstructorDto getById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.map(x -> InstructorDto.builder()
                .name(x.getName())
                .email(x.getEmail())
                .phone(x.getPhone())
                .build())
                .orElseThrow(() -> new InstructorNotFoundException("There is no instructor present with this id: " + id));
    }

    @Override
    public String addInstructor(InstructorDto instructorDto) {
        Optional<Department> department = departmentRepository.findById(Long.valueOf(instructorDto.getDepartmentId()));
        if (!department.isPresent()){
            throw new DepartmentNotFoundException("There is no department present with this id: ");
        }

        User user = User.builder()
                .email(instructorDto.getEmail())
                .password(instructorDto.getPassword())
                .userRole(UserRole.INSTRUCTOR)
                .build();
        userRepository.save(user);

        Instructor instructor = Instructor.builder()
                .name(instructorDto.getName())
                .email(instructorDto.getEmail())
                .phone(instructorDto.getPhone())
                .uuid(UUID.randomUUID().toString())
                .build();
        instructorRepository.save(instructor);
        return null;
    }

    @Override
    public String updateInstructor(Long id, InstructorDto instructorDto) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (!instructor.isPresent()){
            throw new InstructorNotFoundException("There is no instructor present with this id:  " + id);
        }
        instructor.get().setName(instructorDto.getName());
        instructor.get().setEmail(instructorDto.getEmail());
        instructor.get().setPhone(instructorDto.getPhone());
        instructor.get().setPassword(passwordEncoder.encode(instructorDto.getPassword()));
        instructorRepository.save(instructor.get());
        return "Instructor updated with this ID: " + id;
    }

    @Override
    public String deleteInstructor(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (!instructor.isPresent()){
            throw new InstructorNotFoundException("There is no instructor present with this id:  " + id);
        }
        List<Lesson> lessonList = instructor.get().getLessons();
        if (!lessonList.isEmpty()) {
            for (Lesson lesson : lessonList) {
                lesson.setInstructor(null);
            }
            lessonRepository.saveAll(lessonList);
        }
        instructorRepository.delete(instructor.get());
        return "Instructor has been deleted with this ID: " + id;
    }
}
