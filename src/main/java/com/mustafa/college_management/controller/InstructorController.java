package com.mustafa.college_management.controller;

import com.mustafa.college_management.dto.InstructorDto;
import com.mustafa.college_management.service.InstructorService;
import com.mustafa.college_management.service.impl.InstructorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorServiceImpl instructorService;
    public InstructorController(InstructorServiceImpl instructorService) {
        this.instructorService = instructorService;
    }
    @GetMapping
    public ResponseEntity<List<InstructorDto>> getAll(){
        return new ResponseEntity<>(instructorService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable Long id){
        return new ResponseEntity<>(instructorService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addInstructor(@RequestBody InstructorDto instructorDto){
        return new ResponseEntity<>(instructorService.addInstructor(instructorDto),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateInstructor(@PathVariable Long id, @RequestBody InstructorDto instructorDto){
        return new ResponseEntity<>(instructorService.updateInstructor(id,instructorDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long id){
        return new ResponseEntity<>(instructorService.deleteInstructor(id),HttpStatus.OK);
    }
}
