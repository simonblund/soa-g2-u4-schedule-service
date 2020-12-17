package com.g2.scheduleservice.interfaces.rest;

import com.g2.scheduleservice.api.rest.UrlPaths;
import com.g2.scheduleservice.api.rest.course.CourseRequest;
import com.g2.scheduleservice.api.rest.course.CourseResponse;
import com.g2.scheduleservice.api.rest.course.ListCourseResponse;
import com.g2.scheduleservice.application.CourseService;
import com.g2.scheduleservice.domain.DomainObjectMapper;
import com.g2.scheduleservice.infrastructure.generator.TestDataGenerator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @Autowired
    private final TestDataGenerator testDataGenerator;


    @GetMapping(UrlPaths.COURSE_RESOURCE)
    ResponseEntity<ListCourseResponse> getAllCourses(){
        val courses = service.getAll().stream()
                .map(course -> DomainObjectMapper.toCourseResponse(course))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ListCourseResponse.builder().courses(courses).build());
    }

    @GetMapping(UrlPaths.GET_COURSE)
    ResponseEntity<CourseResponse> findOneCourse(@PathVariable String courseCode){
        val course = service.findFromCourseCode(courseCode);
        return ResponseEntity.ok(DomainObjectMapper.toCourseResponse(course));
    }

    @PostMapping(UrlPaths.COURSE_RESOURCE)
    ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest){
        val course = service.create(courseRequest);
        return ResponseEntity.ok(DomainObjectMapper.toCourseResponse(course));
    }

    @PostMapping(UrlPaths.BASE_URI+"/test")
    ResponseEntity<Void> createTestCourses(){
        testDataGenerator.generate();
        return ResponseEntity.accepted().build();
    }

}
