package com.mustafa.college_management.service;

import com.mustafa.college_management.dto.LessonDto;

import java.util.List;

public interface LessonService {
    List<LessonDto> getAll();

    LessonDto getById(Long id);

    String addLesson(LessonDto lessonDto);

    String updateLesson(Long id, LessonDto lessonDto);

    String deleteLesson(Long id);
}
