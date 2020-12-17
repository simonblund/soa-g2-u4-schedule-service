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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long teacherId;
    private String userName;
    private String firstName;
    private String lastName;

    @ManyToMany
    private List<CourseOccasion> courseOccasions;
}
