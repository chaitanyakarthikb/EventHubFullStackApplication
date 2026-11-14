package Eventhub.demo.Event.Utils;


import Eventhub.demo.Event.Entity.Event;
import Eventhub.demo.Event.Repository.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class EventUtils {
    private static EventRepository eventRepository;
    public EventUtils(EventRepository eventRepository){
        EventUtils.eventRepository = eventRepository;
    }
    public static List<Event> getEventsByVenueId(UUID venueId){
        return eventRepository.findByVenue_VenueId(venueId);
    }
}