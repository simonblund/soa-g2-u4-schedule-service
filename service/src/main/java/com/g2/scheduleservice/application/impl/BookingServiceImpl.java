package com.g2.scheduleservice.application.impl;

import com.g2.scheduleservice.application.BookingService;
import com.g2.scheduleservice.domain.Booking;
import com.g2.scheduleservice.domain.BookingDto;
import com.g2.scheduleservice.infrastructure.db.BookingRepository;
import com.g2.scheduleservice.infrastructure.db.ResourceRepository;
import com.g2.scheduleservice.infrastructure.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public Booking bookAThing(BookingDto input) {
        Booking result;

        if(input.isRoom()){
            result = bookingRepository.save(Booking.builder()
                    .room(roomRepository.findById(input.getId()).get())
                    .startAt(input.getStartAt())
                    .endAt(input.getEndAt())
                    .contact(input.getContact())
                    .relatedEvent(input.getRelatedEvent())
                    .build());
        } else {
            result = bookingRepository.save(Booking.builder()
                    .resource(resourceRepository.findById(input.getId()).get())
                    .startAt(input.getStartAt())
                    .endAt(input.getEndAt())
                    .contact(input.getContact())
                    .relatedEvent(input.getRelatedEvent())
                    .build());
        }
        return  result;
    }
}
