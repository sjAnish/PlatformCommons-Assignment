package com.platformcommons.assignment.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platformcommons.assignment.entity.StudentEntity;

public interface StudentRepository
        extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByStudentCode(
            String studentCode);

    Optional<StudentEntity>
    findByStudentCodeAndDateOfBirth(
            String studentCode,
            LocalDate dateOfBirth);

    List<StudentEntity>
    findByStudentNameContainingIgnoreCase(
            String studentName);
}