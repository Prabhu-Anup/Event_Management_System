package com.jsf_mini_project.event_management_system.service.venue;

import com.jsf_mini_project.event_management_system.dto.VenueDto;
import com.jsf_mini_project.event_management_system.exception.VenueNotFoundException;
import com.jsf_mini_project.event_management_system.model.Venue;
import com.jsf_mini_project.event_management_system.repository.VenueRepository;
import com.jsf_mini_project.event_management_system.request.VenueRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService implements IVenueService {
    public final VenueRepository venueRepository;
    private final Venue venue;
    private final ModelMapper modelMapper;

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    public Venue getVenueById(String venueId) {
        return venueRepository.findById(venueId)
                .orElseThrow(() -> new VenueNotFoundException("Venue not found"));
    }

    @Override
    public void addVenue(VenueRequest req) {
        Venue venue = new Venue();
        venue.setName(req.getName());
        venue.setDescription(req.getDescription());
        venue.setCapacity(req.getCapacity());
        venue.setAddress(req.getAddress());
        venueRepository.save(venue);
    }

    @Override
    public void updateVenue(VenueRequest req, String venueId) {
        Venue venue = venueRepository.findVenueById(venueId)
                .orElseThrow(() -> new VenueNotFoundException("Venue not found"));

        venue.setId(venueId);
        venue.setName(req.getName());
        venue.setDescription(req.getDescription());
        venue.setCapacity(req.getCapacity());
        venue.setAddress(req.getAddress());

        venueRepository.save(venue);
    }

    @Override
    public void markIsAvailable(String venueId) {
        if (!venueRepository.existsById(venueId)) {
            throw new VenueNotFoundException("Venue not found");
        }
        venueRepository.markIsAvailable(venueId);
    }
    @Override
    public void deleteVenue(String venueId) {
        if (!venueRepository.existsById(venueId)) {
            throw new VenueNotFoundException("Venue not found");
        }
        venueRepository.deleteById(venueId);
    }

    @Override
    public VenueDto convertToDTO(Venue venue) {
        return modelMapper.map(venue, VenueDto.class);
    }
}
