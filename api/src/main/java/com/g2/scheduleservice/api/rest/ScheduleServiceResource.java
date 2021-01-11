package com.g2.scheduleservice.api.rest;

import com.g2.scheduleservice.api.rest.resource.BookingResponse;
import com.g2.scheduleservice.api.rest.resource.ResourceResponse;
import com.g2.scheduleservice.api.rest.resource.RoomResponse;
import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import com.g2.scheduleservice.api.rest.schedule.ReservationRequest;
import com.g2.scheduleservice.api.rest.schedule.ReservationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ScheduleServiceResource {
    @GetMapping(UrlPaths.GET_FROM_OCCASIONID)
    ResponseEntity<CourseOccasionScheduleResponse> getFromOccasionId(@PathVariable long courseOccasionId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser);

    @PostMapping(UrlPaths.GET_FROM_OCCASIONID)
    ResponseEntity<ReservationResponse> saveToCanvas(@PathVariable long courseOccasionId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser, @RequestBody ReservationRequest request);

    @GetMapping(UrlPaths.BOOKING_RESOURCE)
    ResponseEntity<List<BookingResponse>> getAllBookings();

    @PostMapping(UrlPaths.GET_FROM_RESOURCE_ID)
    ResponseEntity<BookingResponse> bookResource(@PathVariable long resourceId, @RequestBody BookingResponse request);

    @PostMapping(UrlPaths.GET_FROM_ROOM_ID)
    ResponseEntity<BookingResponse> bookRoom(@PathVariable long roomId, @RequestBody BookingResponse request);

    @GetMapping(UrlPaths.BOOKING_RESOURCE_RESOURCE)
    ResponseEntity<List<ResourceResponse>> getAllResources();

    @GetMapping(UrlPaths.BOOKING_ROOM_RESOURCE)
    ResponseEntity<List<RoomResponse>> getAllRooms();
}

