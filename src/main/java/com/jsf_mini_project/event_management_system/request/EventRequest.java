package com.jsf_mini_project.event_management_system.request;

import com.jsf_mini_project.event_management_system.enums.EventStatus;
import com.jsf_mini_project.event_management_system.model.Venue;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    private String name;
    private String description;
    private EventStatus status;
    private LocalDateTime startTime;
    private int duration;
    private String venueId;
}
