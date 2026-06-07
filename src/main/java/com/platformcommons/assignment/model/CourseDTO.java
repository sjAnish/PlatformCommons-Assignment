package com.platformcommons.assignment.model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {


    private String courseName;

    private String description;

    private String courseType;

    private Integer durationInMonths;

    private List<String> topics;
}