package com.g2.scheduleservice.api.rest;

import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ScheduleServiceResource {
    @GetMapping(UrlPaths.GET_FROM_OCCASIONID)
    ResponseEntity<CourseOccasionScheduleResponse> getFromOccasionId(@PathVariable long courseOccasionId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser);

    @PostMapping(UrlPaths.GET_FROM_OCCASIONID)
    ResponseEntity<CourseOccasionScheduleResponse> saveToCanvas(@PathVariable long courseOccasionId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser, @RequestBody CourseOccasionScheduleResponse request );

}
