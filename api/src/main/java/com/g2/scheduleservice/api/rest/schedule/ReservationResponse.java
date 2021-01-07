package com.g2.scheduleservice.api.rest.schedule;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReservationResponse {
    private String location;
    private long userId;
    private String contactName;

    private String distanceUrl;
    private String eventUrl;
    private String title;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Session session;
}
