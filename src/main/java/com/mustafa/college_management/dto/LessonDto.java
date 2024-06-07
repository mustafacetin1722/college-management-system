package com.mustafa.college_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class LessonDto {

    private String courseCode;
    private int courseHours;
    private String courseSubject;
    private String courseDescription;
    private String courseType;
    private String courseLevel;
    private int courseCredits;
    private List<String> courseBooks;
    private String courseLanguage;
    private double courseAverage;
    private Long instructorId;
    private Long departmentId;
}
