package com.g2.scheduleservice.infrastructure.db;

import com.g2.scheduleservice.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findOneByCourseCode(String courseCode);

    @Override
    List<Course> findAll();
}
