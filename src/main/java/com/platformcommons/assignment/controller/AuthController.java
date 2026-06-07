package com.platformcommons.assignment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.assignment.model.AdminLoginDTO;
import com.platformcommons.assignment.model.AuthResponse;
import com.platformcommons.assignment.model.StudentLoginDTO;
import com.platformcommons.assignment.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
name = "Authentication",
description = "Admin and Student Authentication APIs"
)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin/login")
    @Operation( summary = "Admin Login", description = "Authenticate admin and generate JWT token" )
    public AuthResponse adminLogin(
            @RequestBody AdminLoginDTO request) {

        return authService.adminLogin(request);
    }

    @PostMapping("/student/login")
    @Operation( summary = "Student Login", description = "Authenticate student and generate JWT token" )
    public AuthResponse studentLogin(
            @RequestBody StudentLoginDTO request) {

        return authService.studentLogin(request);
    }
}