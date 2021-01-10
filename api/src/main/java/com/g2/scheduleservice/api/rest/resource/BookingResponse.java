package com.g2.scheduleservice.api.rest.resource;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookingResponse {
    private UUID uuid;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String contact;
    private RoomResponse room;
    private ResourceResponse resource;
}
