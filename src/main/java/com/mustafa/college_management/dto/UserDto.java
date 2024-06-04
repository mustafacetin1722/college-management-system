package com.mustafa.college_management.dto;

import com.mustafa.college_management.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private UserRole userRole;
}
