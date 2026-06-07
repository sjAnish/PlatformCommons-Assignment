package com.platformcommons.assignment.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.platformcommons.assignment.entity.StudentEntity;
import com.platformcommons.assignment.repository.StudentRepository;
import com.platformcommons.assignment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	private final StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		return userRepository
				.findByUsername(username).map(user -> User.builder().username(user.getUsername())
						.password(user.getPassword()).roles(user.getRole().name().replace("ROLE_", "")).build())
				.orElseGet(() -> {

					StudentEntity student = studentRepository.findByStudentCode(username)
							.orElseThrow(() -> new RuntimeException("User not found"));

					return User.builder().username(student.getStudentCode()).password("").roles("STUDENT").build();
				});
	}
}