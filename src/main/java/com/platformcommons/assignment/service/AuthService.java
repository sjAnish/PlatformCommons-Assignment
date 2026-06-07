package com.platformcommons.assignment.service;

import com.platformcommons.assignment.model.AdminLoginDTO;
import com.platformcommons.assignment.model.AuthResponse;
import com.platformcommons.assignment.model.StudentLoginDTO;

public interface AuthService {

    AuthResponse adminLogin(AdminLoginDTO request);

    AuthResponse studentLogin(StudentLoginDTO request);
}