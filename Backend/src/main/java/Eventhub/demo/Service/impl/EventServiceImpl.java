package Eventhub.demo.Service.impl;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Repository.EventRepository;
import Eventhub.demo.Repository.Impl.EventRepositoryImpl;
import Eventhub.demo.Service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepositoryImpl eventRepository;

    public EventServiceImpl(EventRepositoryImpl eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(EventReqDTO eventReqDTO) {
        Event event = new Event();
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventId(UUID.randomUUID());
        return eventRepository.createEvent(event);
    }

    @Override
    public Event getEventById(UUID eventId) {
        if (eventRepository.getEventById(eventId).isPresent()){
            return eventRepository.getEventById(eventId).get();
        }
        throw new EventNotFoundException("No Event Found with the specified id : " + eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public Optional<Event> deleteEvent(UUID eventId) {
        Event event  = eventRepository.getEventById(eventId).isPresent() ? eventRepository.getEventById(eventId).get() : null;
        if(event == null){
            throw new EventNotFoundException("No Event Found with the specified id : " + eventId);
        }
        Optional<Event> myEvent =  eventRepository.deleteEvent(eventId);
        return Optional.ofNullable(myEvent).orElseThrow(()->new EventNotFoundException("NO Event Found with the specified id : " + eventId));
    }

    @Override
    public Event updateEvent(EventReqDTO eventReqDTO, UUID eventId) {
        Event event = new Event();
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        return eventRepository.updateEvent(event,eventId);
    }

}
