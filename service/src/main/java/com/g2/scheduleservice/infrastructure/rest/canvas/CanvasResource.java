package com.g2.scheduleservice.infrastructure.rest.canvas;

import com.g2.scheduleservice.infrastructure.rest.ExternalPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CanvasResource {
    @RequestMapping(method = RequestMethod.GET, path = ExternalPaths.CANVAS_CALENDAR)
    ResponseEntity<CanvasCalendarResponse> getCalendarEvent(@PathVariable int eventId, @RequestHeader("Authorization") String token);
}
