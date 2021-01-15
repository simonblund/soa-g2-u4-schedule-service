package com.g2.scheduleservice.api.rest.schedule;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.g2.scheduleservice.api.rest.resource.BookingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReservationResponse {
    private long id;
    private String location;
    private long userId;
    private String contactName;

    private String distanceUrl;
    private String eventUrl;
    private String title;
    private String description;

    private Instant startTime;
    private Instant endTime;

    private List<BookingResponse> rooms;
    private List<BookingResponse> resources;

    private Session session;
}
