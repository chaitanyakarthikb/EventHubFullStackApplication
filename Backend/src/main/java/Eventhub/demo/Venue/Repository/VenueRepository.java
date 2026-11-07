package Eventhub.demo.Venue.Repository;

import Eventhub.demo.Venue.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VenueRepository extends JpaRepository<Venue,UUID> {
    Optional<Venue> getVenueByVenueId(UUID venueId);
    Optional<Venue> deleteVenueByVenueId(UUID venueId);
}
