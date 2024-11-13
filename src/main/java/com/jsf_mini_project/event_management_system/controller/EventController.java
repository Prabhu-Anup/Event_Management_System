package com.jsf_mini_project.event_management_system.controller;

import com.jsf_mini_project.event_management_system.dto.EventDto;
import com.jsf_mini_project.event_management_system.exception.EventNotFoundException;
import com.jsf_mini_project.event_management_system.exception.VenueBookedException;
import com.jsf_mini_project.event_management_system.exception.VenueNotFoundException;
import com.jsf_mini_project.event_management_system.model.Event;
import com.jsf_mini_project.event_management_system.request.EventRequest;
import com.jsf_mini_project.event_management_system.response.ApiResponse;
import com.jsf_mini_project.event_management_system.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/event")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllEvents() {
        try {
            List<EventDto> events = eventService
                    .getAllEvents()
                    .stream()
                    .map(eventService::convertToDTO)
                    .toList();
            return ResponseEntity.ok(new ApiResponse("success", events));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("{eventId}")
    public ResponseEntity<ApiResponse> getEventById(@PathVariable("eventId") String eventId) {
        try {
            Event event = eventService.getEventById(eventId);
            return ResponseEntity.ok(new ApiResponse("success", eventService.convertToDTO(event)));
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createEvent(@RequestBody EventRequest event) {
        try {
            eventService.createEvent(event);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (VenueBookedException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse("error", e.getMessage()));
        } catch (VenueNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PutMapping("{eventId}")
    public ResponseEntity<ApiResponse> updateEvent(@PathVariable("eventId") String eventId, @RequestBody EventRequest event) {
        try {
            eventService.updateEvent(event, eventId);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (EventNotFoundException | VenueNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @DeleteMapping("{eventId}") public ResponseEntity<ApiResponse> deleteEvent(@PathVariable("eventId") String eventId) {
        try {
            eventService.removeEvent(eventId);
            return ResponseEntity.ok(new ApiResponse("success", eventId));
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }
}
