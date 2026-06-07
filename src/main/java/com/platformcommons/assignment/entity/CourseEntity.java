package com.platformcommons.assignment.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.platformcommons.assignment.model.CourseDTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(
	        name = "course_name",
	        nullable = false,
	        unique = true
	)
	private String courseName;

	@Column(name = "description")
	private String description;

	@Column(name = "course_type")
	private String courseType;

	@Column(name = "duration_in_months")
	private Integer durationInMonths;

	@ElementCollection
	@CollectionTable(
	        name = "course_topics",
	        joinColumns = @JoinColumn(
	                name = "course_name",
	                referencedColumnName = "course_name"
	        )
	)
	@Column(name = "topic")
	private List<String> topics;
	
	@ManyToMany(mappedBy = "courses")
	@Builder.Default
	private Set<StudentEntity> students =
	        new HashSet<>();

	public CourseDTO toModel() {

		return CourseDTO.builder().courseName(courseName).description(description).courseType(courseType)
				.durationInMonths(durationInMonths).topics(topics).build();
	}

	public static CourseEntity fromModel(CourseDTO dto) {

		return CourseEntity.builder().courseName(dto.getCourseName()).description(dto.getDescription())
				.courseType(dto.getCourseType()).durationInMonths(dto.getDurationInMonths()).topics(dto.getTopics())
				.build();
	}
}