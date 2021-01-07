package com.g2.scheduleservice.api.rest;

public class UrlPaths {
    public static final String BASE_URI = "/schedule-service";
    public static final String V1 = "/V1";
    public static final String SCHEDULE_RESOURCE = BASE_URI + V1 + "/schedule";
    public static final String GET_FROM_OCCASIONID = SCHEDULE_RESOURCE + "/occasion/{courseOccasionId}";
    public static final String GET_FROM_TIMEEDITOBJECTID = SCHEDULE_RESOURCE + "/timeedit/{teId}";
}

