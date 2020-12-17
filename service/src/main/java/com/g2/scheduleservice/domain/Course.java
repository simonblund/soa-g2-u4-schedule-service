package com.g2.scheduleservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    private String name;
    private String courseCode;
    private double ects;


    @OneToMany(mappedBy = "course")
    private List<CourseOccasion> courseOccasions;

}
