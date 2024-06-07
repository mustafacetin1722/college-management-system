package com.mustafa.college_management.service.impl;

import com.mustafa.college_management.dto.LessonDto;
import com.mustafa.college_management.exception.LessonNotFoundException;
import com.mustafa.college_management.model.Department;
import com.mustafa.college_management.model.Instructor;
import com.mustafa.college_management.model.Lesson;
import com.mustafa.college_management.repository.DepartmentRepository;
import com.mustafa.college_management.repository.InstructorRepository;
import com.mustafa.college_management.repository.LessonRepository;
import com.mustafa.college_management.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;
    public LessonServiceImpl(LessonRepository lessonRepository,
                             DepartmentRepository departmentRepository,
                             InstructorRepository instructorRepository) {
        this.lessonRepository = lessonRepository;
        this.departmentRepository = departmentRepository;
        this.instructorRepository = instructorRepository;
    }
    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.findAll().stream()
                .map(lesson -> LessonDto.builder()
                .courseBooks(lesson.getCourseBooks())
                .courseAverage(lesson.getCourseAverage())
                .courseCredits(lesson.getCourseCredits())
                .courseCode(lesson.getCourseCode())
                .courseDescription(lesson.getCourseDescription())
                .courseHours(lesson.getCourseHours())
                .courseLevel(lesson.getCourseLevel())
                .courseSubject(lesson.getCourseSubject())
                .courseLanguage(lesson.getCourseLanguage())
                .departmentId(lesson.getDepartment().getId())
                .instructorId(lesson.getInstructor().getId())
                .build()).collect(Collectors.toList());
    }

    @Override
    public LessonDto getById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.map(x -> LessonDto.builder()
                .courseBooks(x.getCourseBooks())
                .courseAverage(x.getCourseAverage())
                .courseCredits(x.getCourseCredits())
                .courseCode(x.getCourseCode())
                .courseDescription(x.getCourseDescription())
                .courseHours(x.getCourseHours())
                .courseLevel(x.getCourseLevel())
                .courseSubject(x.getCourseSubject())
                .courseLanguage(x.getCourseLanguage())
                .departmentId(x.getDepartment().getId())
                .instructorId(x.getInstructor().getId())
                .build())
                .orElseThrow(() ->
                        new LessonNotFoundException("There is no lesson present with this id: "+id));
    }

    @Override
    public String addLesson(LessonDto lessonDto) {
        Optional<Department> department = departmentRepository.findById(lessonDto.getDepartmentId());
        Optional<Instructor> instructor = instructorRepository.findById(lessonDto.getInstructorId());
        if (!department.isPresent() || !instructor.isPresent()){
            throw new RuntimeException("Instructor or department not found");
        }
        Lesson lesson = Lesson.builder()
                .courseBooks(lessonDto.getCourseBooks())
                .courseAverage(lessonDto.getCourseAverage())
                .courseCredits(lessonDto.getCourseCredits())
                .courseCode(lessonDto.getCourseCode())
                .courseDescription(lessonDto.getCourseDescription())
                .courseHours(lessonDto.getCourseHours())
                .courseLevel(lessonDto.getCourseLevel())
                .courseSubject(lessonDto.getCourseSubject())
                .courseLanguage(lessonDto.getCourseLanguage())
                .uuid(UUID.randomUUID().toString())
                .department(department.get())
                .instructor(instructor.get())
                .build();
        lessonRepository.save(lesson);
        return "Lesson created successfully";
    }

    @Override
    public String updateLesson(Long id, LessonDto lessonDto) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (!lesson.isPresent()){
            throw new LessonNotFoundException("There is no lesson present with this id: " + id);
        }
        lesson.get().setCourseAverage(lessonDto.getCourseAverage());
        lesson.get().setCourseBooks(lessonDto.getCourseBooks());
        lesson.get().setCourseCredits(lessonDto.getCourseCredits());
        lesson.get().setCourseDescription(lessonDto.getCourseDescription());
        lesson.get().setCourseHours(lessonDto.getCourseHours());
        lesson.get().setCourseLanguage(lessonDto.getCourseLanguage());
        lesson.get().setCourseLevel(lessonDto.getCourseLevel());
        lessonRepository.save(lesson.get());
        return "Lesson updated with this ID: " + id;
    }

    @Override
    public String deleteLesson(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (!lesson.isPresent()){
            throw new LessonNotFoundException("There is no lesson present with this id: " + id);
        }
        lessonRepository.delete(lesson.get());
        return "Lesson has been deleted with this ID: " + id;
    }
}
