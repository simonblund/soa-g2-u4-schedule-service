package com.g2.scheduleservice.infrastructure.rest;

public class ExternalPaths {
    public static final String CANVAS_CALENDAR = "/api/v1/calendar_events/{eventId}";
    public static final String POST_CANVAS = "/api/v1/calendar_events";
    public static final String CANVAS_USER_CALENDAR = "/api/v1/users/{userId}/calendar_events?start_date={startDate}&end_date={endDate}";
    public static final String TE_GET_OBJECT = "?h=t&sid=3&p={firstDate}.x%5C%2C{lastDate}.x&objects={objectId}.28&ox=0&types=0&fe=0";
}
