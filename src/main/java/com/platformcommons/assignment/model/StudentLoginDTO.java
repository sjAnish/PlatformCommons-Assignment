package com.platformcommons.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentLoginDTO {

	private String studentCode;
	private String dateOfBirth;
}