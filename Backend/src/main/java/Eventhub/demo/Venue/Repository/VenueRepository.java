package Eventhub.demo.Venue.Repository;

import Eventhub.demo.Venue.DTO.VenueReqDTO;
import Eventhub.demo.Venue.Entity.Venue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VenueRepository {
    List<Venue> getAllVenues();
    Optional<Venue> getVenueById(UUID venueId);
    Venue createVenue(Venue venue);
    Optional<Venue> deleteVenue(UUID venueId);
    Optional<Venue> updateVenue(UUID venueId, VenueReqDTO venueReqDTO);
}
