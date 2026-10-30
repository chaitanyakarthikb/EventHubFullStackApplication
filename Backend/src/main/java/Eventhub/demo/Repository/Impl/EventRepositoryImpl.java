package Eventhub.demo.Repository.Impl;

import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventRepositoryImpl implements EventRepository {
    ConcurrentHashMap<UUID,Event>events = new ConcurrentHashMap<>();

    @Override
    public Event saveEvent(Event event) {
        events.put(event.getEventId(),event);
        return event;
    }

    @Override
    public Optional<Event> findById(UUID eventId) {
        return Optional.ofNullable(events.get(eventId));
    }

    @Override
    public List<Event> findAllEvents() {
        return new ArrayList<>(events.values());
    }

    @Override
    public Optional<Event> deleteById(UUID eventId) {
        Optional<Event> myEvent = findById(eventId);
        myEvent.ifPresent(event -> events.remove(event.getEventId()));
        return myEvent;
    }
}
