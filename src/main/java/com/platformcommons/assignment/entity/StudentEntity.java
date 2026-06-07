package com.platformcommons.assignment.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.platformcommons.assignment.model.StudentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "student_code", unique = true, nullable = false)
	private String studentCode;

	@Column(nullable = false)
	private String studentName;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "parents_name")
	private String parentsName;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<AddressEntity> addresses = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
	        name = "student_course",
	        joinColumns = @JoinColumn(name = "student_code"),
	        inverseJoinColumns = @JoinColumn(name = "course_name")
	)
	@Builder.Default
	private Set<CourseEntity> courses =
	        new HashSet<>();
	
	public StudentDTO toModel() {

	    return StudentDTO.builder()
	            .studentCode(this.studentCode)
	            .studentName(this.studentName)
	            .dateOfBirth(this.dateOfBirth)
	            .email(this.email)
	            .mobile(this.mobile)
	            .gender(this.gender)
	            .parentsName(this.parentsName)
	            .addresses(
	                    this.addresses == null
	                            ? List.of()
	                            : this.addresses
	                                    .stream()
	                                    .map(AddressEntity::toModel)
	                                    .toList()
	            )
	            .build();
	}

	public static StudentEntity fromModel(
	        StudentDTO dto) {

	    StudentEntity student =
	            StudentEntity.builder()
	                    .studentCode(dto.getStudentCode())
	                    .studentName(dto.getStudentName())
	                    .dateOfBirth(dto.getDateOfBirth())
	                    .email(dto.getEmail())
	                    .mobile(dto.getMobile())
	                    .gender(dto.getGender())
	                    .parentsName(dto.getParentsName())
	                    .addresses(new ArrayList<>())
	                    .build();

	    if(dto.getAddresses() != null) {

	        List<AddressEntity> addresses =
	                dto.getAddresses()
	                        .stream()
	                        .map(AddressEntity::fromModel)
	                        .peek(address ->
	                                address.setStudent(student))
	                        .toList();

	        student.getAddresses()
	                .addAll(addresses);
	    }

	    return student;
	}
}


