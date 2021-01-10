package com.g2.scheduleservice.infrastructure.generator;

import com.g2.scheduleservice.api.rest.resource.ResourceType;
import com.g2.scheduleservice.domain.Booking;
import com.g2.scheduleservice.domain.Resource;
import com.g2.scheduleservice.domain.Room;
import com.g2.scheduleservice.infrastructure.db.BookingRepository;
import com.g2.scheduleservice.infrastructure.db.ResourceRepository;
import com.g2.scheduleservice.infrastructure.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResourceGenerator {
    private final RoomRepository roomRepository;
    private final ResourceRepository resourceRepository;
    private final BookingRepository bookingRepository;

    private List<Room> rooms = new ArrayList<>();

    private List<Resource> resources = new ArrayList<>();

    @PostConstruct
    public void generate(){
        generateRooms();
        generateResources();
        generateBookings();
    }

    private void generateRooms(){
        Room room1 = Room.builder().roomCode("AR01").description("Datasal").build();
        Room room2 = Room.builder().roomCode("AR02").description("Föreläsningsrum").build();
        Room room3 = Room.builder().roomCode("AR03").description("Fin utsikt över vattnet").build();
        Room room4 = Room.builder().roomCode("AR04").description("Grönt rum").build();

        rooms.add(roomRepository.save(room1));
        rooms.add(roomRepository.save(room2));
        rooms.add(roomRepository.save(room3));
        rooms.add(roomRepository.save(room4));
    }

    private void generateResources(){
        Resource res1 = Resource.builder().type(ResourceType.WEBCAM).description("Bredbild").build();
        Resource res2 = Resource.builder().type(ResourceType.PROJECTOR).description("Stor").build();
        Resource res3 = Resource.builder().type(ResourceType.PROJECTOR).description("Liten").build();
        Resource res4 = Resource.builder().type(ResourceType.STUDENT_COMPUTERS).description("30st").build();

        resources.add(resourceRepository.save(res1));
        resources.add(resourceRepository.save(res2));
        resources.add(resourceRepository.save(res3));
        resources.add(resourceRepository.save(res4));
    }

    private void generateBookings(){
        LocalDateTime soon = LocalDateTime.now();
        LocalDateTime plusOne = LocalDateTime.now().plus(1,ChronoUnit.HOURS);
        Booking booking1 = Booking.builder().resource(resources.get(0)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();
        Booking booking2 = Booking.builder().resource(resources.get(1)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();
        Booking booking3 = Booking.builder().resource(resources.get(2)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();
        Booking booking4 = Booking.builder().resource(resources.get(1)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(2, ChronoUnit.DAYS)).build();
        Booking booking5 = Booking.builder().room(rooms.get(0)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();
        Booking booking6 = Booking.builder().room(rooms.get(1)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();
        Booking booking7 = Booking.builder().room(rooms.get(2)).contact("Martin Beck").startAt(soon.plus(1, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();
        Booking booking8 = Booking.builder().room(rooms.get(1)).contact("Martin Beck").startAt(soon.plus(2, ChronoUnit.DAYS)).endAt(plusOne.plus(1, ChronoUnit.DAYS)).build();

        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
        bookingRepository.save(booking3);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);
        bookingRepository.save(booking6);
        bookingRepository.save(booking7);
        bookingRepository.save(booking8);

    }

}
