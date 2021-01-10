package com.g2.scheduleservice.api.rest;

public class UrlPaths {
    public static final String BASE_URI = "/schedule-service";
    public static final String V1 = "/V1";
    public static final String SCHEDULE_RESOURCE = BASE_URI + V1 + "/schedule";
    public static final String GET_FROM_OCCASIONID = SCHEDULE_RESOURCE + "/occasion/{courseOccasionId}";
    public static final String GET_FROM_TIMEEDITOBJECTID = SCHEDULE_RESOURCE + "/timeedit/{teId}";

    public static final String BOOKING_RESOURCE = BASE_URI + V1 + "/bookings";
    public static final String GET_FROM_BOOKING_ID = BOOKING_RESOURCE + "/{bookingId}";
    public static final String BOOKING_ROOM_RESOURCE = BOOKING_RESOURCE + "/rooms";
    public static final String GET_FROM_ROOM_ID = BOOKING_ROOM_RESOURCE +"/{roomId}";
    public static final String BOOKING_RESOURCE_RESOURCE = BOOKING_RESOURCE + "/resources";
    public static final String GET_FROM_RESOURCE_ID = BOOKING_RESOURCE_RESOURCE + "/{resourceId}";
}

