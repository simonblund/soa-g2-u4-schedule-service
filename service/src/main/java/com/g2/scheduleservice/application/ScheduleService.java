package com.g2.scheduleservice.application;

import com.g2.scheduleservice.api.rest.schedule.CourseOccasionScheduleResponse;
import com.g2.scheduleservice.api.rest.schedule.ReservationResponse;

import java.time.LocalDate;

public interface ScheduleService {
    CourseOccasionScheduleResponse getReservations(int objectId, LocalDate firstDate, LocalDate lastDate);
}
