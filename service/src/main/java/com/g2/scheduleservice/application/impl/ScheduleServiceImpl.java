package com.g2.scheduleservice.application.impl;

import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import com.g2.scheduleservice.api.rest.schedule.ReservationResponse;
import com.g2.scheduleservice.application.ScheduleService;
import com.g2.scheduleservice.infrastructure.rest.TimeEditClient;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditReservationResponse;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final TimeEditClient timeEditClient;

    @Override
    public CourseOccasionScheduleResponse getReservations(int objectId, LocalDate firstDate, LocalDate lastDate) {
        System.out.println("FirstDate " + firstDate + " becomes "+ toDateint(firstDate));
        val timeEditResponse = timeEditClient.getObject(objectId, toDateint(firstDate), toDateint(lastDate));

        return  toCourseOccasionScheduleResponse(timeEditResponse.getBody());

    }

    private CourseOccasionScheduleResponse toCourseOccasionScheduleResponse(TimeEditResponse input) {
        val reservations = input.getReservations().stream().map(i -> toReservationResponse(i)).collect(Collectors.toList());
        System.out.println("SNSUSJK");
        return CourseOccasionScheduleResponse.builder()
                .reservations(reservations)
                .CourseCode(reservations.get(0).getDescription().substring(0,5))
                .build();
    }

    private ReservationResponse toReservationResponse(TimeEditReservationResponse input){
        System.out.println("JOIJH"+input);
        return ReservationResponse.builder()
                .contactName(input.getColumns().get(2))
                .title(input.getColumns().get(3))
                .distanceUrl(input.getColumns().get(7))
                .eventUrl("https://cloud.timeedit.net/ltu/web/schedule1/ri.json?h=t&sid=3&objects="+input.getId()+".28&ox=0&types=0&fe=0")
                .location(input.getColumns().get(1))
                .startTime(LocalDateTime.of(input.getStartdate(), input.getStarttime()))
                .endTime(LocalDateTime.of(input.getEnddate(), input.getEndtime()))
                .description(input.getColumns().get(5))
                .build();

    }

    private int toDateint(LocalDate input){
        int year = input.getYear()-2000;
        int month = input.getMonthValue();
        int day = input.getDayOfMonth();

        // This is not a magnificent solution
        return (year * 10000) + (month * 100) + day;
    }
}
