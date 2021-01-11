package com.g2.scheduleservice.application;

import com.g2.scheduleservice.domain.Booking;
import com.g2.scheduleservice.domain.BookingDto;

public interface BookingService {
    Booking bookAThing(BookingDto input);
}
