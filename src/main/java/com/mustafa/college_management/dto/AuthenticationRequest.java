package com.mustafa.college_management.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
