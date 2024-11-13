package com.jsf_mini_project.event_management_system.request;

import lombok.Data;

@Data
public class VenueRequest {
    private String name;
    private String description;
    private String address;
    private int capacity;
}
