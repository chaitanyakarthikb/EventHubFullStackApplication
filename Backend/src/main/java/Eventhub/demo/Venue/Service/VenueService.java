package Eventhub.demo.Venue.Service;


import Eventhub.demo.Venue.DTO.VenueReqDTO;
import Eventhub.demo.Venue.Entity.Venue;

import java.util.List;
import java.util.UUID;

public interface VenueService {
    List<Venue> getAllVenues();
    Venue getVenueById(UUID venueId);
    Venue createVenue(VenueReqDTO venueReqDTO);
    Venue deleteVenue(UUID venueId);
    Venue updateVenue(UUID venueId, VenueReqDTO venueReqDTO);
}
