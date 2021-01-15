package com.g2.scheduleservice.interfaces.rest;

import com.g2.scheduleservice.api.rest.UrlPaths;
import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import com.g2.scheduleservice.api.rest.schedule.ReservationRequest;
import com.g2.scheduleservice.api.rest.schedule.ReservationResponse;
import com.g2.scheduleservice.application.BookingService;
import com.g2.scheduleservice.application.ScheduleService;
import com.g2.scheduleservice.domain.BookingDto;
import com.g2.scheduleservice.domain.DomainObjectMapper;
import com.g2.scheduleservice.infrastructure.rest.CanvasClient;
import com.g2.scheduleservice.infrastructure.rest.CourseServiceClient;
import com.g2.scheduleservice.infrastructure.rest.TimeEditClient;
import com.g2.scheduleservice.infrastructure.rest.canvas.CanvasCalendarResponse;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.ofInstant;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final CanvasClient client;
    private final TimeEditClient timeEditClient;
    private final ScheduleService service;
    private final CourseServiceClient courseServiceClient;
    private final BookingService bookingService;

    @GetMapping(UrlPaths.GET_FROM_OCCASIONID)
    ResponseEntity<CourseOccasionScheduleResponse> getFromOccasionId(@PathVariable long courseOccasionId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser) {
        try {
            val timeEditObject = courseServiceClient.getCourseOccasion(courseOccasionId).getTimeEditObjectId();

            LocalDate startDate = LocalDate.now().minus(3, ChronoUnit.MONTHS);
            LocalDate lastDate = LocalDate.now().plus(3, ChronoUnit.MONTHS);

            CourseOccasionScheduleResponse response;
            if (timeEditObject != 0) {
                response = service.getReservationsTe(
                        timeEditObject,
                        startDate,
                        lastDate);
            } else {
                // Look in canvas
                response = service.getReservationsCa(
                        courseOccasionId,
                        tokenToBearer(canvasToken),
                        canvasUser,
                        startDate,
                        lastDate
                );
            }
            return ResponseEntity.ok(response);


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(UrlPaths.GET_FROM_OCCASIONID)
    ResponseEntity<ReservationResponse> saveToCanvas(@PathVariable long courseOccasionId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser, @RequestBody ReservationRequest request) {
        try {
            val response = service.saveReservations(courseOccasionId, tokenToBearer(canvasToken), canvasUser, request);
            if(request.getRooms() != null && !request.getRooms().isEmpty()){
                val rooms = request.getRooms().stream()
                        .map(i -> bookingService.bookAThing(BookingDto.builder()
                                .room(true)
                                .contact(request.getContactName())
                                .startAt(ofInstant(request.getStartTime(), ZoneId.systemDefault()))
                                .endAt(ofInstant(request.getEndTime(), ZoneId.systemDefault()))
                                //.startAt(request.getStartTime().toLocalDateTime())
                                //.endAt(request.getEndTime().toLocalDateTime())
                                .id(i)
                                .relatedEvent(response.getId())
                                .build()))
                        .map(i -> DomainObjectMapper.toBookingResponse(i))
                        .collect(Collectors.toList());
                response.setRooms(rooms);
            }

            if(request.getResources() != null && !request.getResources().isEmpty()){
                val resources = request.getResources().stream()
                        .map(i -> bookingService.bookAThing(BookingDto.builder()
                                .room(false)
                                .contact(request.getContactName())
                                .startAt(ofInstant(request.getStartTime(), ZoneId.systemDefault()))
                                .endAt(ofInstant(request.getEndTime(), ZoneId.systemDefault()))
                                //.startAt(request.getStartTime().toLocalDateTime())
                                //.endAt(request.getEndTime().toLocalDateTime())
                                .id(i)
                                .relatedEvent(response.getId())
                                .build()))
                        .map(i -> DomainObjectMapper.toBookingResponse(i))
                        .collect(Collectors.toList());
                response.setResources(resources);
            }




            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("test/get/{eventId}")
    ResponseEntity<CanvasCalendarResponse> getCanvasEvent(@PathVariable int eventId, @RequestHeader("CanvasToken") String canvasToken, @RequestHeader("CanvasUser") int canvasUser) {
        return client.getCalendarEvent(eventId, tokenToBearer(canvasToken));
    }

    @GetMapping("test/get/time-edit/{objectId}")
    ResponseEntity<TimeEditResponse> getTEObject(@PathVariable int objectId) {
        CacheControl cacheControl = CacheControl.noCache().sMaxAge(2, TimeUnit.SECONDS);
        log.warn("Request has come in {}", objectId);

        val result = timeEditClient.getObject(objectId, 20200901, 20210120).getBody();
        return ResponseEntity.ok().cacheControl(cacheControl).body(result);
    }

    private String tokenToBearer(String token) {
        return "Bearer " + token;
    }


}
