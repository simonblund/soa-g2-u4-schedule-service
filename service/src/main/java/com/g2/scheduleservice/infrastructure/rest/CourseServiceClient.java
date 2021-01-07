package com.g2.scheduleservice.infrastructure.rest;

import com.g2.courseservice.api.rest.UrlPaths;
import com.g2.courseservice.api.rest.courseinstance.CourseOccasionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-client", url = "${integration.services.course-service.url}")
public interface CourseServiceClient {
    @GetMapping(path = UrlPaths.GET_COURSE_INSTANCE)
    CourseOccasionResponse getCourseOccasion(@PathVariable long courseOccasionId);
}
