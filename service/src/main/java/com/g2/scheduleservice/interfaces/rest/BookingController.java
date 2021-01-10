package com.g2.scheduleservice.interfaces.rest;

import com.g2.scheduleservice.api.rest.UrlPaths;
import com.g2.scheduleservice.api.rest.resource.BookingResponse;
import com.g2.scheduleservice.api.rest.resource.ResourceResponse;
import com.g2.scheduleservice.api.rest.resource.RoomResponse;
import com.g2.scheduleservice.domain.Booking;
import com.g2.scheduleservice.domain.DomainObjectMapper;
import com.g2.scheduleservice.infrastructure.db.BookingRepository;
import com.g2.scheduleservice.infrastructure.db.ResourceRepository;
import com.g2.scheduleservice.infrastructure.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookingController {
    private final BookingRepository bookingRepository;
    private final ResourceRepository resourceRepository;
    private final RoomRepository roomRepository;

    @GetMapping(UrlPaths.BOOKING_RESOURCE)
    ResponseEntity<List<BookingResponse>> getAllBookings(){
        val response = bookingRepository.findAll().stream()
                .map(i -> DomainObjectMapper.toBookingResponse(i))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PostMapping(UrlPaths.GET_FROM_RESOURCE_ID)
    ResponseEntity<BookingResponse> bookResource(@PathVariable long resourceId, @RequestBody BookingResponse request){
        val resource = resourceRepository.findById(resourceId);
        val response = bookingRepository.save(
                Booking.builder()
                .resource(resource.get())
                .contact(request.getContact())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .build()
        );
        return ResponseEntity.ok(DomainObjectMapper.toBookingResponse(response));
    }

    @PostMapping(UrlPaths.GET_FROM_ROOM_ID)
    ResponseEntity<BookingResponse> bookRoom(@PathVariable long roomId, @RequestBody BookingResponse request){
        val room = roomRepository.findById(roomId);
        val response = bookingRepository.save(
                Booking.builder()
                        .room(room.get())
                        .contact(request.getContact())
                        .startAt(request.getStartAt())
                        .endAt(request.getEndAt())
                        .build()
        );
        return ResponseEntity.ok(DomainObjectMapper.toBookingResponse(response));
    }

    @GetMapping(UrlPaths.BOOKING_RESOURCE_RESOURCE)
    ResponseEntity<List<ResourceResponse>> getAllResources(){
        val resources =resourceRepository.findAll();
        val response = resources.stream().map(i-> DomainObjectMapper.toResourceResponse(i))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping(UrlPaths.BOOKING_ROOM_RESOURCE)
    ResponseEntity<List<RoomResponse>> getAllRooms(){
        val resources =roomRepository.findAll();
        val response = resources.stream().map(i-> DomainObjectMapper.toRoomResponse(i))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
