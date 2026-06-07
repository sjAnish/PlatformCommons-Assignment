package com.platformcommons.assignment.service.impl;

import java.time.LocalDate;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.platformcommons.assignment.entity.StudentEntity;
import com.platformcommons.assignment.entity.UserEntity;
import com.platformcommons.assignment.model.AdminLoginDTO;
import com.platformcommons.assignment.model.AuthResponse;
import com.platformcommons.assignment.model.StudentLoginDTO;
import com.platformcommons.assignment.repository.StudentRepository;
import com.platformcommons.assignment.repository.UserRepository;
import com.platformcommons.assignment.security.JwtService;
import com.platformcommons.assignment.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final JwtService jwtService;

	@Override
	public AuthResponse adminLogin(AdminLoginDTO request) {

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		} catch (Exception ex) {
			throw new RuntimeException( "Invalid username or password", ex);
		}

		UserEntity user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("Admin not found"));

		UserDetails details = org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).roles("ADMIN").build();

		return new AuthResponse(jwtService.generateToken(details));
	}

	@Override
	public AuthResponse studentLogin(StudentLoginDTO request) {

		StudentEntity student = studentRepository
				.findByStudentCodeAndDateOfBirth(request.getStudentCode(), LocalDate.parse(request.getDateOfBirth()))
				.orElseThrow(() -> new RuntimeException("Invalid student credentials"));

		UserDetails details = org.springframework.security.core.userdetails.User.builder()
				.username(student.getStudentCode()).password("").roles("STUDENT").build();

		return new AuthResponse(jwtService.generateToken(details));
	}
}