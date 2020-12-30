package com.g2.scheduleservice.interfaces.rest;

import com.g2.scheduleservice.infrastructure.rest.CanvasClient;
import com.g2.scheduleservice.infrastructure.rest.TimeEditClient;
import com.g2.scheduleservice.infrastructure.rest.canvas.CanvasCalendarResponse;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final CanvasClient client;
    private final TimeEditClient timeEditClient;

    @GetMapping("get/{eventId}")
    ResponseEntity<CanvasCalendarResponse> getCanvasEvent(@PathVariable int eventId, @RequestHeader("CanvasToken") String canvasToken) {
        return client.getCalendarEvent(eventId, tokenToBearer(canvasToken));
    }

    @GetMapping("get/time-edit/{objectId}")
    ResponseEntity<TimeEditResponse> getTEObject(@PathVariable int objectId){
        CacheControl cacheControl = CacheControl.noCache().sMaxAge(2, TimeUnit.SECONDS);
        log.warn("Request has come in {}", objectId);

        val result = timeEditClient.getObject(objectId).getBody();
        return ResponseEntity.ok().cacheControl(cacheControl).body(result);
    }

    private String tokenToBearer(String token){
        return "Bearer " + token;
    }


}
