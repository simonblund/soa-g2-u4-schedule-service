package com.g2.scheduleservice.application;

import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import com.g2.scheduleservice.api.rest.schedule.ReservationResponse;

import java.time.LocalDate;

public interface ScheduleService {
    CourseOccasionScheduleResponse getReservationsTe(long objectId, LocalDate firstDate, LocalDate lastDate);
    CourseOccasionScheduleResponse getReservationsCa(long courseOccasionId, String canvasToken, int canvasUserId, LocalDate firstDate, LocalDate lastDate);
    CourseOccasionScheduleResponse saveReservations(long courseOccasionId, String canvasToken, int canvasUserId, CourseOccasionScheduleResponse input);
}
