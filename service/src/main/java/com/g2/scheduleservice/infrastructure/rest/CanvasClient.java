package com.g2.scheduleservice.infrastructure.rest;


import com.g2.studentservice.api.mock.canvas.CanvasResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "canvas-client", url = "${integration.services.mock-service.url}")
public interface CanvasClient extends CanvasResource {
}
