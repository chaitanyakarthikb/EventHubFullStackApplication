package Eventhub.demo.Event.Utils;
import Eventhub.demo.Event.Repository.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class EventUtils {
    private final EventRepository eventRepository;

    public EventUtils(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public boolean hasEventByVenueId(UUID venueId){
        return eventRepository.existsByVenue_VenueId(venueId);
    }
}