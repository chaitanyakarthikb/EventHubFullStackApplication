package Eventhub.demo.Repository.Impl;

import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private final ConcurrentHashMap<UUID,Event>events = new ConcurrentHashMap<>();

    @Override
    public Event createEvent(Event event) {
        events.put(event.getEventId(),event);
        return event;
    }

    @Override
    public Optional<Event> getEventById(UUID eventId) {
        return Optional.ofNullable(events.get(eventId));
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    @Override
    public Optional<Event> deleteEvent(UUID eventId) {
        Event event = events.get(eventId);
        if(event == null){
            throw new EventNotFoundException("No Event found with the Event ID mentioned " + eventId);
        }
        return Optional.of(event);
    }

    @Override
    public Event updateEvent(Event event, UUID eventId) {
        events.put(eventId,event);
        return event;
    }
}
