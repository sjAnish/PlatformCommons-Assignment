package com.platformcommons.assignment.service;

import com.platformcommons.assignment.model.ApiResponseDTO;
import com.platformcommons.assignment.model.CourseAssignmentDTO;
import com.platformcommons.assignment.model.StudentDTO;

public interface StudentService {

	ApiResponseDTO<StudentDTO> updateProfile( StudentDTO studentDTO);

	ApiResponseDTO<StudentDTO> getStudentByCode(String studentCode);

	ApiResponseDTO<String> leaveCourse(CourseAssignmentDTO dto);
}