package com.platformcommons.assignment.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {

    private String studentCode;

    @NotBlank(message = "Student Name is required")
    private String studentName;

    @NotNull(message = "Date Of Birth is required")
    private LocalDate dateOfBirth;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Mobile is required")
    private String mobile;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Parents Name is required")
    private String parentsName;

    @Valid
    private List<AddressDTO> addresses;
}