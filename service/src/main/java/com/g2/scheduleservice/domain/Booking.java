package com.g2.scheduleservice.domain;

import com.g2.scheduleservice.api.rest.resource.ResourceResponse;
import com.g2.scheduleservice.api.rest.resource.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String contact;


    @ManyToOne
    private Room room;

    @ManyToOne
    private Resource resource;

    public Optional<Room> getRoom(){
        return Optional.ofNullable(room);
    }

    public Optional<Resource> getResource(){
        return Optional.ofNullable(resource);
    }
}
