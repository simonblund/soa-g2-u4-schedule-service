package com.g2.scheduleservice.infrastructure.rest.canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CanvasCalendarEvent {

    @NonNull
    private String contextCode;

    private long id;
    private String title;
    private String description;
    private Instant startAt;
    private Instant endAt;
    private String locationName;
    private String locationAdress;
    private boolean allDay;

}
