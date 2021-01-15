package com.g2.scheduleservice.infrastructure.rest.timeedit;

import com.g2.scheduleservice.infrastructure.rest.ExternalPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TimeEditResource {
    /*
    firstDate = reservations are fetched from this date and until endDate - 20201120
    lastDate = until this date - 20210201
    objectId the secret id of the thing we want to fetch - 132867
     */
    @RequestMapping(method = RequestMethod.GET, path = ExternalPaths.TE_GET_OBJECT)
    ResponseEntity<TimeEditResponse> getObject(@PathVariable long objectId, @PathVariable int firstDate, @PathVariable int lastDate);
}
