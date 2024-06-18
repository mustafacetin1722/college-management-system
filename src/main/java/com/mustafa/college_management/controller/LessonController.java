package com.mustafa.college_management.controller;

import com.mustafa.college_management.dto.LessonDto;
import com.mustafa.college_management.service.impl.LessonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonServiceImpl lessonService;
    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping
    public ResponseEntity<List<LessonDto>> getAll(){
        return new ResponseEntity<>(lessonService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id){
        return new ResponseEntity<>(lessonService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addLesson(@RequestBody LessonDto lessonDto){
        return new ResponseEntity<>(lessonService.addLesson(lessonDto),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateLesson(@PathVariable Long id, @RequestBody LessonDto lessonDto){
        return new ResponseEntity<>(lessonService.updateLesson(id,lessonDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long id){
        return new ResponseEntity<>(lessonService.deleteLesson(id),HttpStatus.OK);
    }
}
