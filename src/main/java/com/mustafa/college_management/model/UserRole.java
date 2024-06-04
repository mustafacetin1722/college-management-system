package com.mustafa.college_management.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" +name();
    }
}
