package com.g2.scheduleservice.api.rest.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum Session {
    PASS1(8,15, 9,45),
    PASS2(10,15, 11,45),
    PASS3(13,00, 14,30),
    PASS4(14,45, 16,15),
    PASS5(16,30, 18,00);


    private final int startTimeHour;
    private final int startTimeMinute;
    private final int endTimeHour;
    private final int endTimeMinute;

    Session(int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute){
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;

        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
    }

    public LocalTime getStartTime(){
        return LocalTime.of(startTimeHour, startTimeMinute);
    }

    public LocalTime getEndTime(){
        return LocalTime.of(endTimeHour, endTimeMinute);
    }


}
