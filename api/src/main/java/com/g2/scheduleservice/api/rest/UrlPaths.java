package com.g2.scheduleservice.api.rest;

public class UrlPaths {
    public static final String BASE_URI = "/course-service";
    public static final String V1 = "/V1";
    public static final String COURSE_RESOURCE = BASE_URI + V1 + "/courses";
    public static final String GET_COURSE = COURSE_RESOURCE + "/{courseCode}";

    public static final String COURSE_INSTANCES = GET_COURSE + "/instances";
    public static final String GET_COURSE_INSTANCE = COURSE_INSTANCES + "/{courseOccasionId}";

    public static final String TEACHER_RESOURCE = BASE_URI + V1 + "/teachers";
    public static final String GET_TEACHER = TEACHER_RESOURCE + "/{teacherId}";
}

