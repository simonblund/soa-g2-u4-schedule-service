package com.g2.scheduleservice.infrastructure.rest.canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CanvasCalendarResponse {
    private int id;
    private String startAt;
    private String endAt;

    // The HTML description of the event
    private String description;
    private String locationName;
    private String locationAddress;
    private String contextCode;
    private int effectiveContextCode;
    private String workflowState;
    private String url;


    /*

  "description": "<b>It's that time again!</b>",
          // The location name of the event
          "location_name": "Greendale Community College",
          // The address where the event is taking place
          "location_address": "Greendale, Colorado",
          // the context code of the calendar this event belongs to (course, user or
          // group)
          "context_code": "course_123",
          // if specified, it indicates which calendar this event should be displayed on.
          // for example, a section-level event would have the course's context code here,
          // while the section's context code would be returned above)
          "effective_context_code": null,
          // the context name of the calendar this event belongs to (course, user or
          // group)
          "context_name": "Chemistry 101",
          // a comma-separated list of all calendar contexts this event is part of
          "all_context_codes": "course_123,course_456",
          // Current state of the event ('active', 'locked' or 'deleted') 'locked'
          // indicates that start_at/end_at cannot be changed (though the event could be
          // deleted). Normally only reservations or time slots with reservations are
          // locked (see the Appointment Groups API)
          "workflow_state": "active",
          // Whether this event should be displayed on the calendar. Only true for
          // course-level events with section-level child events.
          "hidden": false,
          // Normally null. If this is a reservation (see the Appointment Groups API), the
          // id will indicate the time slot it is for. If this is a section-level event,
          // this will be the course-level parent event.
          "parent_event_id": null,
          // The number of child_events. See child_events (and parent_event_id)
          "child_events_count": 0,
          // Included by default, but may be excluded (see include[] option). If this is a
          // time slot (see the Appointment Groups API) this will be a list of any
          // reservations. If this is a course-level event, this will be a list of
          // section-level events (if any)
          "child_events": null,
          // URL for this calendar event (to update, delete, etc.)
          "url": "https://example.com/api/v1/calendar_events/234",
          // URL for a user to view this event
          "html_url": "https://example.com/calendar?event_id=234&include_contexts=course_123",
          // The date of this event
          "all_day_date": "2012-07-19",
          // Boolean indicating whether this is an all-day event (midnight to midnight)
          "all_day": false,
          // When the calendar event was created
          "created_at": "2012-07-12T10:55:20-06:00",
          // When the calendar event was last updated
          "updated_at": "2012-07-12T10:55:20-06:00",
          // Various Appointment-Group-related fields.These fields are only pertinent to
          // time slots (appointments) and reservations of those time slots. See the
          // Appointment Groups API. The id of the appointment group
          "appointment_group_id": null,
          // The API URL of the appointment group
          "appointment_group_url": null,
          // If the event is a reservation, this a boolean indicating whether it is the
          // current user's reservation, or someone else's
          "own_reservation": false,
          // If the event is a time slot, the API URL for reserving it
          "reserve_url": null,
          // If the event is a time slot, a boolean indicating whether the user has
          // already made a reservation for it
          "reserved": false,
          // The type of participant to sign up for a slot: 'User' or 'Group'
          "participant_type": "User",
          // If the event is a time slot, this is the participant limit
          "participants_per_appointment": null,
          // If the event is a time slot and it has a participant limit, an integer
          // indicating how many slots are available
          "available_slots": null,
          // If the event is a user-level reservation, this will contain the user
          // participant JSON (refer to the Users API).
          "user": null,
          // If the event is a group-level reservation, this will contain the group
          // participant JSON (refer to the Groups API).
          "group": null

     */

}
