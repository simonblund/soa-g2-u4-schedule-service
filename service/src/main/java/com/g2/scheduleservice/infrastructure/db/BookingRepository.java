package com.g2.scheduleservice.infrastructure.db;

import com.g2.scheduleservice.domain.Booking;
import com.g2.scheduleservice.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends CrudRepository<Booking, UUID> {
    @Override
    List<Booking> findAll();

}
