package com.platformcommons.assignment.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platformcommons.assignment.entity.CourseEntity;
import com.platformcommons.assignment.entity.StudentEntity;
import com.platformcommons.assignment.model.ApiResponseDTO;
import com.platformcommons.assignment.model.CourseAssignmentDTO;
import com.platformcommons.assignment.model.CourseDTO;
import com.platformcommons.assignment.model.StudentDTO;
import com.platformcommons.assignment.repository.CourseRepository;
import com.platformcommons.assignment.repository.StudentRepository;
import com.platformcommons.assignment.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

	private final StudentRepository studentRepository;

	private final CourseRepository courseRepository;

	@Override
	public ApiResponseDTO<StudentDTO> createStudent(StudentDTO studentDTO) {

		try {

			if (studentDTO == null) {

				return ApiResponseDTO.<StudentDTO>builder().success(false).message("Student request cannot be null")
						.data(null).timestamp(LocalDateTime.now()).build();
			}

			StudentEntity student = StudentEntity.fromModel(studentDTO);

			student.setStudentCode(generateStudentCode());

			StudentEntity savedStudent = studentRepository.save(student);

			return ApiResponseDTO.<StudentDTO>builder().success(true).message("Student created successfully")
					.data(savedStudent.toModel()).timestamp(LocalDateTime.now()).build();

		} catch (Exception ex) {

			return ApiResponseDTO.<StudentDTO>builder().success(false).message("Error occurred while creating student")
					.data(null).timestamp(LocalDateTime.now()).build();
		}
	}
	
	@Override
	public ApiResponseDTO<CourseDTO> createCourse(
	        CourseDTO courseDTO) {

	    try {

	        if (courseDTO == null) {

	            return ApiResponseDTO
	                    .<CourseDTO>builder()
	                    .success(false)
	                    .message(
	                            "Course request cannot be null")
	                    .data(null)
	                    .timestamp(
	                            LocalDateTime.now())
	                    .build();
	        }

	        if (courseDTO.getCourseName() == null ||
	                courseDTO.getCourseName().isBlank()) {

	            return ApiResponseDTO
	                    .<CourseDTO>builder()
	                    .success(false)
	                    .message(
	                            "Course name is required")
	                    .data(null)
	                    .timestamp(
	                            LocalDateTime.now())
	                    .build();
	        }

	        CourseEntity course =
	                CourseEntity.fromModel(
	                        courseDTO);

	        CourseEntity savedCourse =
	                courseRepository.save(
	                        course);

	        return ApiResponseDTO
	                .<CourseDTO>builder()
	                .success(true)
	                .message(
	                        "Course created successfully")
	                .data(
	                        savedCourse.toModel())
	                .timestamp(
	                        LocalDateTime.now())
	                .build();

	    } catch (Exception ex) {

	        return ApiResponseDTO
	                .<CourseDTO>builder()
	                .success(false)
	                .message(
	                        "Error occurred while creating course")
	                .data(null)
	                .timestamp(
	                        LocalDateTime.now())
	                .build();
	    }
	}
	
	@Override
	public ApiResponseDTO<String> assignCourse(CourseAssignmentDTO dto) {

		try {

			if (dto == null) {

				return ApiResponseDTO.<String>builder().success(false)
						.message("Course assignment request cannot be null").data(null).timestamp(LocalDateTime.now())
						.build();
			}

			if (dto.getStudentCode() == null || dto.getStudentCode().isBlank()) {

				return ApiResponseDTO.<String>builder().success(false).message("Student code is required").data(null)
						.timestamp(LocalDateTime.now()).build();
			}

			if (dto.getCourseName() == null || dto.getCourseName().isBlank()) {

				return ApiResponseDTO.<String>builder().success(false).message("Course name is required").data(null)
						.timestamp(LocalDateTime.now()).build();
			}

			StudentEntity student = studentRepository.findByStudentCode(dto.getStudentCode()).orElse(null);

			if (student == null) {

				return ApiResponseDTO.<String>builder().success(false)
						.message("Student not found with code : " + dto.getStudentCode()).data(null)
						.timestamp(LocalDateTime.now()).build();
			}

			CourseEntity course = courseRepository.findByCourseName(dto.getCourseName()).orElse(null);

			if (course == null) {

				return ApiResponseDTO.<String>builder().success(false)
						.message("Course not found : " + dto.getCourseName()).data(null).timestamp(LocalDateTime.now())
						.build();
			}

			if (student.getCourses().contains(course)) {

				return ApiResponseDTO.<String>builder().success(false).message("Course already assigned to student")
						.data(null).timestamp(LocalDateTime.now()).build();
			}

			student.getCourses().add(course);

			studentRepository.save(student);

			return ApiResponseDTO.<String>builder().success(true).message("Course assigned successfully")
					.data("SUCCESS").timestamp(LocalDateTime.now()).build();

		} catch (Exception ex) {

			return ApiResponseDTO.<String>builder().success(false).message("Error occurred while assigning course")
					.data(null).timestamp(LocalDateTime.now()).build();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponseDTO<List<StudentDTO>> getStudentsByCourse(String courseName) {

		try {

			if (courseName == null || courseName.isBlank()) {

				return ApiResponseDTO.<List<StudentDTO>>builder().success(false).message("Course name is required")
						.data(null).timestamp(LocalDateTime.now()).build();
			}

			CourseEntity course = courseRepository.findByCourseName(courseName).orElse(null);

			if (course == null) {

				return ApiResponseDTO.<List<StudentDTO>>builder().success(false)
						.message("Course not found : " + courseName).data(null).timestamp(LocalDateTime.now()).build();
			}

			List<StudentDTO> students = course.getStudents().stream().map(StudentEntity::toModel).toList();

			return ApiResponseDTO.<List<StudentDTO>>builder().success(true).message("Students fetched successfully")
					.data(students).timestamp(LocalDateTime.now()).build();

		} catch (Exception ex) {

			return ApiResponseDTO.<List<StudentDTO>>builder().success(false)
					.message("Error occurred while fetching students").data(null).timestamp(LocalDateTime.now())
					.build();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponseDTO<List<StudentDTO>> searchStudentsByName(String studentName) {

		try {

			if (studentName == null || studentName.isBlank()) {

				return ApiResponseDTO.<List<StudentDTO>>builder().success(false).message("Student name is required")
						.data(null).timestamp(LocalDateTime.now()).build();
			}

			List<StudentDTO> students = studentRepository.findByStudentNameContainingIgnoreCase(studentName).stream()
					.map(StudentEntity::toModel).toList();

			if (students.isEmpty()) {

				return ApiResponseDTO.<List<StudentDTO>>builder().success(false)
						.message("No students found with name : " + studentName).data(List.of())
						.timestamp(LocalDateTime.now()).build();
			}

			return ApiResponseDTO.<List<StudentDTO>>builder().success(true).message("Students fetched successfully")
					.data(students).timestamp(LocalDateTime.now()).build();

		} catch (Exception ex) {

			return ApiResponseDTO.<List<StudentDTO>>builder().success(false)
					.message("Error occurred while searching students").data(null).timestamp(LocalDateTime.now())
					.build();
		}
	}
	
	private String generateStudentCode() {

		return "STU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

}
