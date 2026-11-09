package Eventhub.demo.Venue.Utils;

import Eventhub.demo.Venue.Entity.Venue;
import Eventhub.demo.Venue.Repository.VenueRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
public class VenueUtils {
    private final VenueRepository venueRepository;

    public VenueUtils(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }
    public  Venue getVenueById(UUID venueId){
        Optional<Venue> myVenue = venueRepository.findById(venueId);
        return myVenue.orElse(null);
    }
}
