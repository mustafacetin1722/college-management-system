package com.mustafa.college_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FacultyDto {

    private Long id;
    private String name;
    private String campus;
}
