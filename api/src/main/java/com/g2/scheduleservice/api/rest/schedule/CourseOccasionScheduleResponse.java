package com.g2.scheduleservice.api.rest.schedule;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CourseOccasionScheduleResponse {
    private long courseId;
    private String CourseCode;
    private long courseOccasionId;
    private long timeEditObjectId;

    private List<ReservationResponse> reservations;


}
