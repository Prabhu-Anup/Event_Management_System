package com.jsf_mini_project.event_management_system.repository;

import com.jsf_mini_project.event_management_system.model.Venue;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue,String> {
    @NonNull
    Optional<Venue> findVenueById(@NonNull String id);

    @Modifying
    @Query("UPDATE Venue SET isAvailable=true WHERE id=:venueId")
    void markIsAvailable(String venueId);
}
