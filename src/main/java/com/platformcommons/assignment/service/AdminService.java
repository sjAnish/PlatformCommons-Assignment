package com.platformcommons.assignment.service;

import java.util.List;

import com.platformcommons.assignment.model.ApiResponseDTO;
import com.platformcommons.assignment.model.CourseAssignmentDTO;
import com.platformcommons.assignment.model.CourseDTO;
import com.platformcommons.assignment.model.StudentDTO;

public interface AdminService {

	ApiResponseDTO<StudentDTO> createStudent(
            StudentDTO studentDTO);
	
    ApiResponseDTO<String> assignCourse(
            CourseAssignmentDTO dto);

    ApiResponseDTO<List<StudentDTO>>
    getStudentsByCourse(
            String courseName);

    ApiResponseDTO<List<StudentDTO>>
    searchStudentsByName(
            String studentName);
    
    ApiResponseDTO<CourseDTO> createCourse(
            CourseDTO courseDTO);
}