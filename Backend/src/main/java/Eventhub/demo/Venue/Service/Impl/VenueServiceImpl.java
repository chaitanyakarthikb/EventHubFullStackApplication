package Eventhub.demo.Venue.Service.Impl;

import Eventhub.demo.Common.Exception.VenueNotFoundException;
import Eventhub.demo.Venue.DTO.VenueReqDTO;
import Eventhub.demo.Venue.Entity.Venue;
import Eventhub.demo.Venue.Repository.VenueRepository;
import Eventhub.demo.Venue.Service.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }
    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.getAllVenues();
    }

    @Override
    public Venue getVenueById(UUID venueId) {
        Optional<Venue>myVenue = venueRepository.getVenueById(venueId);
        log.info("Fetching venue with ID: {}", venueId);
        if (myVenue.isEmpty()){
            log.warn("Venue not found with the ID : {}" , venueId);
            throw new VenueNotFoundException("Venue not found with the ID : " + venueId);
        }
        return myVenue.get();
    }

    @Override
    public Venue createVenue(VenueReqDTO venueReqDTO) {
        Venue venue = new Venue();
        venue.setVenueId(UUID.randomUUID());
        venue.setVenueName(venueReqDTO.getVenueName());
        venue.setAddress(venueReqDTO.getAddress());
        venue.setMaxCapacity(venueReqDTO.getMaxCapacity());
        Venue venueCreated = venueRepository.createVenue(venue);
        log.info("Venue Created : {}",venueCreated.toString());
        return venueCreated;
    }

    @Override
    public Venue deleteVenue(UUID venueId) {
        log.info("Deleting venue with ID: {}", venueId);
        Optional<Venue>venue = venueRepository.deleteVenue(venueId);
        if (venue.isEmpty()){
            log.warn("Venue not found with the ID : {}" , venueId);
            throw new VenueNotFoundException("Venue not found with the ID : " + venueId);
        }
        return venue.get();
    }

    @Override
    public Venue updateVenue(UUID venueId, VenueReqDTO venueReqDTO) {
        log.info("Updating venue with ID : {}",venueId);
        Optional<Venue> updatedVenue = venueRepository.updateVenue(venueId,venueReqDTO);
        if (updatedVenue.isEmpty()){
            log.warn("Venue not found with the ID : {}" , venueId);
            throw new VenueNotFoundException("Venue not found with the ID : " + venueId);
        }
        return updatedVenue.get();
    }
}
