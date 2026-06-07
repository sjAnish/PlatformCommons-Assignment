package com.platformcommons.assignment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.assignment.model.ApiResponseDTO;
import com.platformcommons.assignment.model.CourseAssignmentDTO;
import com.platformcommons.assignment.model.CourseDTO;
import com.platformcommons.assignment.model.StudentDTO;
import com.platformcommons.assignment.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Operations", description = "Course Assignment and Student Search APIs")
public class AdminController {

	private final AdminService adminService;

	@Operation(summary = "Create Student", description = "Admin can create/admit a new student")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student created successfully") })
	@PostMapping("/create-student")
	public ResponseEntity<ApiResponseDTO<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO) {

		return ResponseEntity.ok(adminService.createStudent(studentDTO));
	}
	
	@Operation(summary = "Create Course", description = "Admin can create a new course")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PostMapping("/create-course")
	public ResponseEntity<ApiResponseDTO<CourseDTO>> createCourse(@RequestBody CourseDTO courseDTO) {

		return ResponseEntity.ok(adminService.createCourse(courseDTO));
	}

	@Operation(summary = "Assign Course To Student", description = "Assign a course to a student")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course assigned successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PostMapping("/assign-course")
	public ResponseEntity<ApiResponseDTO<String>> assignCourse(@RequestBody CourseAssignmentDTO dto) {

		return ResponseEntity.ok(adminService.assignCourse(dto));
	}

	@Operation(summary = "Search Students By Name", description = "Search students using student name")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Students fetched successfully") })
	@GetMapping("/students/search")
	public ResponseEntity<ApiResponseDTO<List<StudentDTO>>> searchStudent(@RequestParam String studentName) {

		return ResponseEntity.ok(adminService.searchStudentsByName(studentName));
	}

	@Operation(summary = "Get Students Assigned To Course", description = "Fetch all students assigned to a specific course")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Students fetched successfully") })
	@GetMapping("/courses/{courseName}/students")
	public ResponseEntity<ApiResponseDTO<List<StudentDTO>>> getStudentsByCourse(@PathVariable String courseName) {

		return ResponseEntity.ok(adminService.getStudentsByCourse(courseName));
	}

}
