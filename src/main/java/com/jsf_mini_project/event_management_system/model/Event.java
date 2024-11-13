package com.jsf_mini_project.event_management_system.model;

import com.jsf_mini_project.event_management_system.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private LocalDateTime startTime;
    private int duration;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;
}
