package Eventhub.demo.Event.Repository;

import Eventhub.demo.Event.Entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface EventRepository{
     Event saveEvent(Event event);
     Optional<Event> findById(UUID eventId);
     List<Event> findAllEvents();
     Optional<Event> deleteById(UUID eventId);
}
