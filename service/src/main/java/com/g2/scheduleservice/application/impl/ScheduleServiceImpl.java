package com.g2.scheduleservice.application.impl;

import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import com.g2.scheduleservice.api.rest.schedule.ReservationRequest;
import com.g2.scheduleservice.api.rest.schedule.ReservationResponse;
import com.g2.scheduleservice.api.rest.schedule.Session;
import com.g2.scheduleservice.application.ScheduleService;
import com.g2.scheduleservice.infrastructure.rest.CanvasClient;
import com.g2.scheduleservice.infrastructure.rest.TimeEditClient;
import com.g2.scheduleservice.infrastructure.rest.canvas.CanvasCalendarEvent;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditReservationResponse;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.ofInstant;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final TimeEditClient timeEditClient;
    private final CanvasClient canvasClient;

    @Override
    public CourseOccasionScheduleResponse getReservationsTe(long objectId, LocalDate firstDate, LocalDate lastDate) {
        System.out.println("FirstDate " + firstDate + " becomes " + toDateint(firstDate));
        val timeEditResponse = timeEditClient.getObject(objectId, toDateint(firstDate), toDateint(lastDate));

        return toCourseOccasionScheduleResponse(timeEditResponse.getBody());

    }

    @Override
    public CourseOccasionScheduleResponse getReservationsCa(long courseOccasionId, String canvasToken, int canvasUserId, LocalDate firstDate, LocalDate lastDate) {

        val canvasResponse = canvasClient.getUserCalendar(canvasUserId, canvasToken, firstDate, lastDate).getBody();
        val reservations = canvasResponse.stream()
                .filter(l -> l.getTitle().contains(String.valueOf(courseOccasionId)))
                .map(this::toReservationResponse)
                .collect(Collectors.toList());
        return CourseOccasionScheduleResponse.builder()
                .reservations(reservations)
                .build();

    }

    @Override
    public ReservationResponse saveReservations(long courseOccasionId, String canvasToken, int canvasUserId, ReservationRequest input){
        val canvasEvent = toCanvasCalendarEvent(input, canvasUserId, courseOccasionId);
        val response = canvasClient.saveToUserCalendar(canvasToken,toFormParams(canvasEvent)).getBody();

        return ReservationResponse.builder()
                .id(response.getId())
                .title(response.getTitle())
                .description(response.getDescription())
                .contactName(response.getDescription())
                .location(response.getLocationName())
                .distanceUrl(response.getDescription())
                .startTime(response.getStartAt())
                .endTime(response.getEndAt())
                .build();
    }

    private Map<String, ?> toFormParams(CanvasCalendarEvent input){
        Map<String, String> result = new HashMap<>();

        result.put("calendar_event[context_code]", input.getContextCode());
        result.put("calendar_event[title]", input.getTitle());
        result.put("calendar_event[description]", input.getDescription());
        result.put("calendar_event[start_at]", input.getStartAt().toString());
        result.put("calendar_event[end_at]", input.getEndAt().toString());
        result.put("calendar_event[location_name]", input.getLocationName());
        //result.put("calendar_event[location_name]", input.getLocationName());
        return result;

    }

    private CanvasCalendarEvent toCanvasCalendarEvent(ReservationResponse input, int canvasUserId, long courseOccasionId){

        String description = input.getDescription()
                + "\n distance-url: " + input.getDistanceUrl()
                + "\n contact: " + input.getContactName();
        return CanvasCalendarEvent.builder()
                .contextCode("user_"+canvasUserId)
                .title(courseOccasionId+" : "+input.getTitle())
                .description(description)
                .startAt(input.getStartTime())
                .endAt(input.getEndTime())
                .locationName(input.getLocation())
                .locationAdress(input.getLocation())
                .build();
    }

    private CanvasCalendarEvent toCanvasCalendarEvent(ReservationRequest input, int canvasUserId, long courseOccasionId){
        String description = input.getDescription()
                + "\n distance-url: " + input.getDistanceUrl()
                + "\n contact: " + input.getContactName();
        return CanvasCalendarEvent.builder()
                .contextCode("user_"+canvasUserId)
                .title(courseOccasionId+" : "+input.getTitle())
                .description(description)
                .startAt(input.getStartTime())
                .endAt(input.getEndTime())
                .locationName(input.getLocation())
                .locationAdress(input.getLocation())
                .build();
    }

    private ReservationResponse toReservationResponse(CanvasCalendarEvent input){
        return ReservationResponse.builder()
                .id(input.getId())
                .title(input.getTitle())
                .description(input.getDescription())
                .startTime(input.getStartAt())
                .endTime(input.getEndAt())
                //.startTime(input.getStartAt())
                //.endTime(input.getEndAt())
                .eventUrl("url"+input.getId())
                .distanceUrl(input.getDescription().toUpperCase())
                .location(input.getLocationName())
                .contactName(input.getDescription())
                .build();
    }


    private CourseOccasionScheduleResponse toCourseOccasionScheduleResponse(TimeEditResponse input) {
        val reservations = input.getReservations().stream().map(i -> toReservationResponse(i)).collect(Collectors.toList());
        System.out.println("SNSUSJK");
        return CourseOccasionScheduleResponse.builder()
                .reservations(reservations)
                .CourseCode(reservations.get(0).getDescription().substring(0, 5))
                .build();
    }


    private ReservationResponse toReservationResponse(TimeEditReservationResponse input) {
        System.out.println("JOIJH" + input);
        return ReservationResponse.builder()
                .contactName(input.getColumns().get(2))
                .title(input.getColumns().get(3))
                .distanceUrl(input.getColumns().get(7))
                .eventUrl("https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&objects=" + input.getId() + ".28&ox=0&types=0&fe=0")
                .location(input.getColumns().get(1))
                .startTime(LocalDateTime.of(input.getStartdate(), input.getStarttime()).toInstant(ZoneOffset.UTC))
                .endTime(LocalDateTime.of(input.getEnddate(), input.getEndtime()).toInstant(ZoneOffset.UTC))
                //.startTime(LocalDateTime.of(input.getStartdate(), input.getStarttime()))
                //.endTime(LocalDateTime.of(input.getEnddate(), input.getEndtime()))
                .description(input.getColumns().get(5))
                .build();

    }

    private int toDateint(LocalDate input) {
        int year = input.getYear() - 2000;
        int month = input.getMonthValue();
        int day = input.getDayOfMonth();

        // This is not a magnificent solution
        return (year * 10000) + (month * 100) + day;
    }
}
