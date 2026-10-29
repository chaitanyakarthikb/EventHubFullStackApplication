package Eventhub.demo.Repository;

import Eventhub.demo.Entity.Event;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository{
     Event saveEvent(Event event);
     Optional<Event> findById(UUID eventId);
     List<Event> findAllEvents();
     Optional<Event> deleteById(UUID eventId);
}
