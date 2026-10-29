package Eventhub.demo.Repository;

import Eventhub.demo.Entity.Event;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository{
     Event createEvent(Event event);
     Optional<Event> getEventById(UUID eventId);
     List<Event> getAllEvents();
     Optional<Event> deleteEvent(UUID eventId);
     Event updateEvent(Event event,UUID eventId);
}
