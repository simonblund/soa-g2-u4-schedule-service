package com.g2.scheduleservice.infrastructure.rest;



import com.g2.scheduleservice.infrastructure.rest.canvas.CanvasResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "real-canvas-client", url = "${integration.services.real-canvas.url}")
public interface CanvasClient extends CanvasResource {
}
