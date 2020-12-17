package com.g2.scheduleservice.infrastructure.rest;

import com.g2.studentservice.api.rest.StudentResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "student-client", url = "${integration.services.student-service.url}")
public interface StudentServiceClient extends StudentResource {

}
