package com.g2.scheduleservice.infrastructure.rest.canvas;

import com.g2.scheduleservice.infrastructure.rest.ExternalPaths;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CanvasResource {
    @RequestMapping(method = RequestMethod.GET, path = ExternalPaths.CANVAS_CALENDAR)
    ResponseEntity<CanvasCalendarResponse> getCalendarEvent(@PathVariable int eventId, @RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.GET, path = ExternalPaths.CANVAS_USER_CALENDAR)
    ResponseEntity<List<CanvasCalendarEvent>> getUserCalendar(
            @PathVariable int userId,
            @RequestHeader("Authorization") String token,
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    );

    @RequestMapping(method = RequestMethod.POST, path = ExternalPaths.POST_CANVAS, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<CanvasCalendarEvent> saveToUserCalendar(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, ?> postParams
            );


}
