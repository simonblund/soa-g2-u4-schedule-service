package com.g2.scheduleservice.domain;

import com.g2.scheduleservice.api.rest.resource.BookingResponse;
import com.g2.scheduleservice.api.rest.resource.ResourceResponse;
import com.g2.scheduleservice.api.rest.resource.RoomResponse;

public class DomainObjectMapper {

    public static BookingResponse toBookingResponse(Booking input) {
        RoomResponse room = null;
        ResourceResponse resource = null;
        if (input.getRoom().isPresent()) {
            room = toRoomResponse(input.getRoom().get());
        } else {
            resource = toResourceResponse(input.getResource().get());
        }

        return BookingResponse.builder()
                .room(room)
                .resource(resource)
                .relevantEvent(input.getRelatedEvent())
                .contact(input.getContact())
                .startAt(input.getStartAt())
                .endAt(input.getEndAt())
                .uuid(input.getUuid())
                .build();
    }

    public static RoomResponse toRoomResponse(Room input) {
        return RoomResponse.builder()
                .roomCode(input.getRoomCode())
                .id(input.getId())
                .description(input.getDescription())
                .build();
    }

    public static ResourceResponse toResourceResponse(Resource input) {
        return ResourceResponse.builder()
                .id(input.getId())
                .type(input.getType())
                .description(input.getDescription())
                .build();
    }
}
