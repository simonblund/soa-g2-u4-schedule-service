package com.g2.scheduleservice.infrastructure.rest;

import com.g2.studentservice.api.mock.ladok.LadokResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ladok-client", url = "${integration.services.mock-service.url}")
public interface LadokClient extends LadokResource {
}
