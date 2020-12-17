package com.g2.scheduleservice.domain;

import com.g2.scheduleservice.api.rest.course.CourseResponse;
import com.g2.scheduleservice.api.rest.courseinstance.CourseOccasionResponse;
import com.g2.scheduleservice.api.rest.teacher.TeacherResponse;
import lombok.val;

import java.util.stream.Collectors;

public class DomainObjectMapper {
    public static CourseResponse toCourseResponse(Course input){
        val instances = input.getCourseOccasions().stream()
                .map(courseOccasion -> toCourseInstanceResponse(courseOccasion))
                .collect(Collectors.toList());
        return CourseResponse.builder()
                .courseId(input.getCourseId())
                .courseCode(input.getCourseCode())
                .ects(input.getEcts())
                .name(input.getCourseCode())
                .courseInstances(instances)
                .build();
    }

    public static TeacherResponse toTeacherResponse(Teacher input){
        return TeacherResponse.builder()
                .teacherId(input.getTeacherId())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .userName(input.getUserName())
                .build();
    }

    public static CourseOccasionResponse toCourseInstanceResponse(CourseOccasion input){
        val teachers = input.getTeachers().stream()
                .map(teacher -> toTeacherResponse(teacher))
                .collect(Collectors.toList());
        return CourseOccasionResponse.builder()
                .courseOccasionId(input.getCourseOccasionId())
                .courseCode(input.getCourse().getCourseCode())
                .location(input.getLocation())
                .year(input.getYear())
                .periods(input.getPeriods())
                .teachers(teachers)
                .build();
    }

}
