package com.g2.scheduleservice.application.impl;

import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

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
    public CourseOccasionScheduleResponse saveReservations(long courseOccasionId, String canvasToken, int canvasUserId, CourseOccasionScheduleResponse input){
        val result = input.getReservations()
                .stream()
                .map(i -> toCanvasCalendarEvent(i, canvasUserId, courseOccasionId))
                .map(i -> canvasClient.saveToUserCalendar(canvasToken,i).getBody())
                .map(i -> toReservationResponse(i))
                .collect(Collectors.toList());
        return CourseOccasionScheduleResponse.builder().reservations(result).build();
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

    private ReservationResponse toReservationResponse(CanvasCalendarEvent input){
        return ReservationResponse.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .startTime(input.getStartAt())
                .endTime(input.getEndAt())
                .eventUrl("url"+input.getId())
                .distanceUrl(input.getDescription().toUpperCase())
                .location(input.getLocationName())
                .contactName(input.getDescription())
                .session(toSession(input.getStartAt()))
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

    private Session toSession(LocalDateTime startTime){
        int hour = startTime.toLocalTime().getHour();

        switch (hour){
            case 8:
                return Session.PASS1;

            case 10:
                return Session.PASS2;

            case 13:
                return Session.PASS3;
            case 14:
                return Session.PASS4;
            case 16:
                return Session.PASS5;
            default:
                return Session.PASS1;
        }
    }

    private ReservationResponse toReservationResponse(TimeEditReservationResponse input) {
        System.out.println("JOIJH" + input);
        return ReservationResponse.builder()
                .contactName(input.getColumns().get(2))
                .title(input.getColumns().get(3))
                .distanceUrl(input.getColumns().get(7))
                .eventUrl("https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&objects=" + input.getId() + ".28&ox=0&types=0&fe=0")
                .location(input.getColumns().get(1))
                .startTime(LocalDateTime.of(input.getStartdate(), input.getStarttime()))
                .endTime(LocalDateTime.of(input.getEnddate(), input.getEndtime()))
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
