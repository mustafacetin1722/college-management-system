package com.mustafa.college_management.controller;

import com.mustafa.college_management.beans.AuthenticationResponse;
import com.mustafa.college_management.dto.AuthenticationRequest;
import com.mustafa.college_management.dto.UserDto;
import com.mustafa.college_management.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserServiceImpl userService;
    public AuthenticationController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registration(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(userService.authenticate(request),HttpStatus.CREATED);
    }
}
