package com.jsf_mini_project.event_management_system.service.venue;

import com.jsf_mini_project.event_management_system.dto.VenueDto;
import com.jsf_mini_project.event_management_system.model.Venue;
import com.jsf_mini_project.event_management_system.request.VenueRequest;

import java.util.List;

public interface IVenueService {
    List<Venue> getAllVenues();
    Venue getVenueById(String venueId);
    void addVenue(VenueRequest venue);
    void updateVenue(VenueRequest venue, String venueId);
    void markIsAvailable(String venueId);
    void deleteVenue(String venueId);
    VenueDto convertToDTO(Venue venue);
}
