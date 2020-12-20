package com.g2.scheduleservice.infrastructure.rest;

import com.g2.scheduleservice.infrastructure.rest.canvas.CanvasResource;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "timeedit-client", url = "${integration.services.time-edit.url}")
public interface TimeEditClient extends TimeEditResource {
}
