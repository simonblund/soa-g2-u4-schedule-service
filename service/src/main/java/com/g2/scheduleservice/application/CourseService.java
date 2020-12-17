package com.g2.scheduleservice.application;

import com.g2.scheduleservice.api.rest.course.CourseRequest;
import com.g2.scheduleservice.domain.Course;

import java.util.List;

public interface CourseService {
    Course create(CourseRequest course);
    Course findFromCourseCode(String courseCode);
    List<Course> getAll();
}
