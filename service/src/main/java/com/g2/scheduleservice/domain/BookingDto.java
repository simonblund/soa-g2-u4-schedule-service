package com.g2.scheduleservice.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {
    private boolean room;
    private long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String contact;
    private long relatedEvent;
}
