package com.jsf_mini_project.event_management_system.controller;

import com.jsf_mini_project.event_management_system.dto.VenueDto;
import com.jsf_mini_project.event_management_system.exception.VenueNotFoundException;
import com.jsf_mini_project.event_management_system.model.Venue;
import com.jsf_mini_project.event_management_system.request.VenueRequest;
import com.jsf_mini_project.event_management_system.response.ApiResponse;
import com.jsf_mini_project.event_management_system.service.venue.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/venue")
public class VenueController {
    private final VenueService venueService;
    private final Venue venue;

    @GetMapping
    public ResponseEntity<ApiResponse> getVenue() {
        try {
            List<VenueDto> venues = venueService.getAllVenues().stream().map(
                    venueService::convertToDTO
            ).toList();
            return ResponseEntity.ok(new ApiResponse("success", venues));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("{venueId}")
    public ResponseEntity<ApiResponse> getVenueById(@PathVariable("venueId") String venueId) {
        try {
            Venue venue = venueService.getVenueById(venueId);
            return ResponseEntity.ok(new ApiResponse("success", venueService.convertToDTO(venue)));
        } catch (VenueNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createVenue(@RequestBody VenueRequest venue) {
        try {
            venueService.addVenue(venue);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PutMapping("{venueId}")
    public ResponseEntity<ApiResponse> updateVenue(@PathVariable("venueId") String venueId, @RequestBody VenueRequest venue) {
        try {
            venueService.updateVenue(venue, venueId);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (VenueNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @DeleteMapping("{venueId}")
    public ResponseEntity<ApiResponse> deleteVenue(@PathVariable("venueId") String venueId) {
        try {
            venueService.deleteVenue(venueId);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (VenueNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }
}
