package com.platformcommons.assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.assignment.model.ApiResponseDTO;
import com.platformcommons.assignment.model.CourseAssignmentDTO;
import com.platformcommons.assignment.model.StudentDTO;
import com.platformcommons.assignment.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')")
@Tag(name = "Student Management", description = "Student Admission and Profile Management APIs")
public class StudentController {

	private final StudentService studentService;

	@Operation(summary = "Update Student Profile", description = "Student can update profile details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Profile updated successfully") })
	@PatchMapping("/{update-student}")
	public ResponseEntity<ApiResponseDTO<StudentDTO>> updateProfile(

			@RequestBody StudentDTO studentDTO) {

		return ResponseEntity.ok(studentService.updateProfile(studentDTO));
	}

	@Operation(summary = "Get Student Profile", description = "Fetch student profile details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student profile fetched successfully") })
	@GetMapping("/{studentCode}")
	public ResponseEntity<ApiResponseDTO<StudentDTO>> getProfile(@PathVariable String studentCode) {

		return ResponseEntity.ok(studentService.getStudentByCode(studentCode));
	}

	@Operation(summary = "Leave Course", description = "Student can leave an assigned course")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course left successfully") })
	@DeleteMapping("/leave-course")
	public ResponseEntity<ApiResponseDTO<String>> leaveCourse(@RequestBody CourseAssignmentDTO dto) {

		return ResponseEntity.ok(studentService.leaveCourse(dto));
	}

}
