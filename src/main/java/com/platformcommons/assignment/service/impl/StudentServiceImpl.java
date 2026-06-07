package com.platformcommons.assignment.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platformcommons.assignment.entity.CourseEntity;
import com.platformcommons.assignment.entity.StudentEntity;
import com.platformcommons.assignment.model.ApiResponseDTO;
import com.platformcommons.assignment.model.CourseAssignmentDTO;
import com.platformcommons.assignment.model.StudentDTO;
import com.platformcommons.assignment.repository.StudentRepository;
import com.platformcommons.assignment.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Override
	public ApiResponseDTO<StudentDTO> updateProfile(StudentDTO studentDTO) {

		try {
            String studentCode = studentDTO.getStudentCode();
			StudentEntity existingStudent = studentRepository.findByStudentCode(studentCode).orElse(null);

			if (existingStudent == null) {

				return ApiResponseDTO.<StudentDTO>builder().success(false).message("Student not found").data(null)
						.timestamp(LocalDateTime.now()).build();
			}

			
		   StudentEntity updatedStudent = StudentEntity.builder()
					.studentName(studentDTO.getStudentName())
					.dateOfBirth(studentDTO.getDateOfBirth()).email(studentDTO.getEmail())
					.mobile(studentDTO.getMobile()).parentsName(studentDTO.getParentsName())
					.gender(studentDTO.getGender())
					.build();
            
			  studentRepository.save(updatedStudent);

			return ApiResponseDTO.<StudentDTO>builder().success(true).message("Profile updated successfully")
					.timestamp(LocalDateTime.now()).build();

		} catch (Exception ex) {

			return ApiResponseDTO.<StudentDTO>builder().success(false).message("Error occurred while updating profile")
					.data(null).timestamp(LocalDateTime.now()).build();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ApiResponseDTO<StudentDTO> getStudentByCode(String studentCode) {

		try {

			StudentEntity student = studentRepository.findByStudentCode(studentCode).orElse(null);

			if (student == null) {

				return ApiResponseDTO.<StudentDTO>builder().success(false).message("Student not found").data(null)
						.timestamp(LocalDateTime.now()).build();
			}

			return ApiResponseDTO.<StudentDTO>builder().success(true).message("Student fetched successfully")
					.data(student.toModel()).timestamp(LocalDateTime.now()).build();

		} catch (Exception ex) {

			return ApiResponseDTO.<StudentDTO>builder().success(false).message("Error occurred while fetching student")
					.data(null).timestamp(LocalDateTime.now()).build();
		}
	}
	
	@Override
	public ApiResponseDTO<String>
	leaveCourse(
	        CourseAssignmentDTO dto) {

	    try {

	        if (dto == null) {

	            return ApiResponseDTO.<String>builder()
	                    .success(false)
	                    .message(
	                            "Request cannot be null")
	                    .data(null)
	                    .timestamp(
	                            LocalDateTime.now())
	                    .build();
	        }

	        StudentEntity student =
	                studentRepository
	                        .findByStudentCode(
	                                dto.getStudentCode())
	                        .orElse(null);

	        if (student == null) {

	            return ApiResponseDTO.<String>builder()
	                    .success(false)
	                    .message(
	                            "Student not found")
	                    .data(null)
	                    .timestamp(
	                            LocalDateTime.now())
	                    .build();
	        }

	        CourseEntity course =
	                student.getCourses()
	                        .stream()
	                        .filter(c ->
	                                c.getCourseName()
	                                        .equalsIgnoreCase(
	                                                dto.getCourseName()))
	                        .findFirst()
	                        .orElse(null);

	        if (course == null) {

	            return ApiResponseDTO.<String>builder()
	                    .success(false)
	                    .message(
	                            "Course not assigned to student")
	                    .data(null)
	                    .timestamp(
	                            LocalDateTime.now())
	                    .build();
	        }

	        student.getCourses()
	                .remove(course);

	        studentRepository.save(student);

	        return ApiResponseDTO.<String>builder()
	                .success(true)
	                .message(
	                        "Course left successfully")
	                .data("SUCCESS")
	                .timestamp(
	                        LocalDateTime.now())
	                .build();

	    } catch (Exception ex) {

	        return ApiResponseDTO.<String>builder()
	                .success(false)
	                .message(
	                        "Error occurred while leaving course")
	                .data(null)
	                .timestamp(
	                        LocalDateTime.now())
	                .build();
	    }
	}

}
