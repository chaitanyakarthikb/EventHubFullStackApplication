package Eventhub.demo.Event.Repository;

import Eventhub.demo.Event.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event,UUID> {
    boolean existsByVenue_VenueId(UUID venueId);
}
