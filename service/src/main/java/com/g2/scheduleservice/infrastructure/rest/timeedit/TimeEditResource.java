package com.g2.scheduleservice.infrastructure.rest.timeedit;

import com.g2.scheduleservice.infrastructure.rest.ExternalPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TimeEditResource {
    @RequestMapping(method = RequestMethod.GET, path = ExternalPaths.TE_GET_OBJECT, consumes = "application/json")
    ResponseEntity<TimeEditResponse> getObject(@PathVariable int objectId);
}
