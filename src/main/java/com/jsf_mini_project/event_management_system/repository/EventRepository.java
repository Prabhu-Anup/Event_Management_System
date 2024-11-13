package com.jsf_mini_project.event_management_system.repository;

import com.jsf_mini_project.event_management_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
