package com.jsf_mini_project.event_management_system.service.event;

import com.jsf_mini_project.event_management_system.dto.EventDto;
import com.jsf_mini_project.event_management_system.exception.EventNotFoundException;
import com.jsf_mini_project.event_management_system.exception.VenueBookedException;
import com.jsf_mini_project.event_management_system.exception.VenueNotFoundException;
import com.jsf_mini_project.event_management_system.model.Event;
import com.jsf_mini_project.event_management_system.model.Venue;
import com.jsf_mini_project.event_management_system.repository.EventRepository;
import com.jsf_mini_project.event_management_system.repository.VenueRepository;
import com.jsf_mini_project.event_management_system.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Couldn't find event"));
    }

    @Override
    public void createEvent(EventRequest req) {
        Venue venue = venueRepository.findById(req.getVenueId())
                .orElseThrow(() -> new VenueNotFoundException("Couldn't find venue"));

        if (!venue.getIsAvailable()) throw new VenueBookedException("Venue is already booked");

        Event event = new Event();
        event.setName(req.getName());
        event.setDescription(req.getDescription());
        event.setDuration(req.getDuration());
        event.setStartTime(req.getStartTime());
        event.setStatus(req.getStatus());
        event.setVenue(venue);

        eventRepository.save(event);
    }

    @Override
    public void updateEvent(EventRequest req, String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Couldn't find event"));

        event.setId(id);
        event.setName(req.getName());
        event.setDescription(req.getDescription());
        event.setDuration(req.getDuration());
        event.setStartTime(req.getStartTime());
        event.setStatus(req.getStatus());
        eventRepository.save(event);
    }

    @Override
    public void removeEvent(String id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException("Couldn't find event");
        }

        eventRepository.deleteById(id);
    }

    @Override
    public EventDto convertToDTO(Event event) {
        return modelMapper.map(event, EventDto.class);
    }
}
