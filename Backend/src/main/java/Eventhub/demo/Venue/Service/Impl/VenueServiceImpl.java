package Eventhub.demo.Venue.Service.Impl;

import Eventhub.demo.Common.Exception.BadRequestException;
import Eventhub.demo.Common.Exception.VenueNotFoundException;
import Eventhub.demo.Event.Entity.Event;
import Eventhub.demo.Event.Utils.EventUtils;
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
    private final EventUtils eventUtils;

    public VenueServiceImpl(VenueRepository venueRepository,EventUtils eventUtils) {
        this.venueRepository = venueRepository;
        this.eventUtils = eventUtils;
    }
    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    public Venue getVenueById(UUID venueId) {
        Optional<Venue>myVenue = venueRepository.findById(venueId);
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
        venue.setVenueName(venueReqDTO.getVenueName());
        venue.setAddress(venueReqDTO.getAddress());
        venue.setMaxCapacity(venueReqDTO.getMaxCapacity());
        Venue venueCreated = venueRepository.save(venue);
        log.info("Venue Created : {}", venueCreated);
        return venueCreated;
    }

    @Override
    public Venue deleteVenue(UUID venueId) {
        log.info("Deleting venue with ID: {}", venueId);
        Optional<Venue> existingVenue = venueRepository.findById(venueId);
        if (existingVenue.isEmpty()){
            log.warn("Venue not found with the ID : {}" , venueId);
            throw new VenueNotFoundException("Venue not found with the ID : " + venueId);
        }
        if (eventUtils.hasEventsByVenueId(venueId)){
            log.warn("Cannot delete venue with ID: {} as it has associated events", venueId);
            throw new BadRequestException("Cannot delete venue with ID " + venueId + " because it has associated events.");
        }
        Venue myExistingVenue = existingVenue.get();
        venueRepository.delete(myExistingVenue);
        return myExistingVenue;
    }

    @Override
    public Venue updateVenue(UUID venueId, VenueReqDTO venueReqDTO) {
        log.info("Updating venue with ID : {}",venueId);
        Optional<Venue> existingVenue = venueRepository.findById(venueId);
        if (existingVenue.isEmpty()){
            log.warn("Venue not found with the ID : {}" , venueId);
            throw new VenueNotFoundException("Venue not found with the ID : " + venueId);
        }
        Venue venue = existingVenue.get();
        venue.setVenueName(venueReqDTO.getVenueName());
        venue.setAddress(venueReqDTO.getAddress());
        venue.setMaxCapacity(venueReqDTO.getMaxCapacity());
        return venueRepository.save(venue);
    }
}
