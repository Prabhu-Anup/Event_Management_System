package com.jsf_mini_project.event_management_system.dto;

import com.jsf_mini_project.event_management_system.enums.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private String id;
    private String name;
    private String description;
    private EventStatus status;
    private LocalDateTime startTime;
    private int duration;
    private String venueId;
}
