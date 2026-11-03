package Eventhub.demo.Venue.Repository.Impl;

import Eventhub.demo.Venue.DTO.VenueReqDTO;
import Eventhub.demo.Venue.Entity.Venue;
import Eventhub.demo.Venue.Repository.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class VenueRepositoryImpl implements VenueRepository {

    ConcurrentHashMap<UUID, Venue> Venues = new ConcurrentHashMap<>();

    @Override
    public List<Venue> getAllVenues() {
        return new ArrayList<>(Venues.values());
    }

    @Override
    public Optional<Venue> getVenueById(UUID venueId) {
        if (Venues.containsKey(venueId)){
            return Optional.of(Venues.get(venueId));
        }
        return Optional.empty();
    }

    @Override
    public Venue createVenue(Venue venue) {
        Venues.put(venue.getVenueId(),venue);
        return venue;
    }

    @Override
    public Optional<Venue> deleteVenue(UUID venueId) {
        if (Venues.containsKey(venueId)){
            Venue myVenue = Venues.get(venueId);
            Venues.remove(venueId);
            return Optional.of(myVenue);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Venue> updateVenue(UUID venueId, VenueReqDTO venueReqDTO) {
        if (Venues.containsKey(venueId)){
            Venue v = Venues.get(venueId);
            v.setVenueName(venueReqDTO.getVenueName());
            v.setAddress(venueReqDTO.getAddress());
            v.setMaxCapacity(venueReqDTO.getMaxCapacity());
            Venues.put(venueId,v);
            return Optional.of(v);
        }
        return Optional.empty();
    }
}
