package com.mustafa.college_management.service.impl;

import com.mustafa.college_management.dto.StudentDto;
import com.mustafa.college_management.exception.DepartmentNotFoundException;
import com.mustafa.college_management.exception.StudentNotFoundException;
import com.mustafa.college_management.model.*;
import com.mustafa.college_management.repository.DepartmentRepository;
import com.mustafa.college_management.repository.LessonRepository;
import com.mustafa.college_management.repository.StudentRepository;
import com.mustafa.college_management.repository.UserRepository;
import com.mustafa.college_management.service.StudentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final LessonRepository lessonRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository,
                              DepartmentRepository departmentRepository, LessonRepository lessonRepository,
                              BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.lessonRepository = lessonRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(student -> StudentDto.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .semester(student.getSemester())
                .phoneNumber(student.getPhoneNumber())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(x -> StudentDto.builder()
                .firstName(x.getFirstName())
                .lastName(x.getLastName())
                .email(x.getEmail())
                .semester(x.getSemester())
                .phoneNumber(x.getPhoneNumber())
                .build())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id. "));
    }

    @Override
    public String addStudent(StudentDto studentDto) {
        Optional<Department> department = departmentRepository.findById(studentDto.getDepartmentId());
        if (!department.isPresent()){
            throw new DepartmentNotFoundException("There is no department present with this id: ");
        }

        Student student = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .semester(studentDto.getSemester())
                .phoneNumber(studentDto.getPhoneNumber())
                .uuid(UUID.randomUUID().toString())
                .userRole(UserRole.valueOf("STUDENT"))
                .build();

        Set<Lesson> lessons = new HashSet<>();
        for (Long lesson : studentDto.getLessonIds()){
            Optional<Lesson> lessonOptional = lessonRepository.findById(lesson);
            if (!lessonOptional.isPresent()){
                throw new RuntimeException("There is no student present with this id: ");
            }
            lessons.add(lessonOptional.get());
        }
        student.setLessons(lessons);
        studentRepository.save(student);

        return "Student created, id: " + student.getId();
    }

    @Override
    public String updateStudent(Long id, StudentDto studentDto) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()){
            throw new StudentNotFoundException("Student not found with id. ");
        }
        student.get().setFirstName(studentDto.getFirstName());
        student.get().setLastName(studentDto.getLastName());
        student.get().setEmail(studentDto.getEmail());
        student.get().setSemester(studentDto.getSemester());
        student.get().setPhoneNumber(studentDto.getPhoneNumber());

        studentRepository.save(student.get());

        return "Student updated with this ID: "+ id;
    }

    @Override
    public String deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()){
            throw new StudentNotFoundException("Student not found with id. ");
        }
        if (student.get().getLessons().isEmpty()){
            for (Lesson lesson : student.get().getLessons()){
                lesson.getStudents().remove(lesson);
            }
        }
        return "Student has been deleted with this ID: "+ id;
    }
}
