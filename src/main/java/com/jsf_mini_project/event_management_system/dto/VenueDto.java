package com.jsf_mini_project.event_management_system.dto;

import lombok.Data;

@Data
public class VenueDto {
    private String id;
    private String name;
    private String description;
    private String address;
    private int capacity;
}
