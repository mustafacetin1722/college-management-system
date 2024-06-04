package com.mustafa.college_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DepartmentDto {

    private String name;
    private String facultyId;
}
