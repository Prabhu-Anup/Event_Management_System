package com.jsf_mini_project.event_management_system.service.event;

import com.jsf_mini_project.event_management_system.dto.EventDto;
import com.jsf_mini_project.event_management_system.model.Event;
import com.jsf_mini_project.event_management_system.request.EventRequest;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();

    Event getEventById(String id);

    void createEvent(EventRequest event);

    void updateEvent(EventRequest event,String id);

    void removeEvent(String id);

    EventDto convertToDTO(Event event);
}
