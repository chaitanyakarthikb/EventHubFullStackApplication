package Eventhub.demo.Service.impl;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.BadRequestException;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Repository.EventRepository;
import Eventhub.demo.Repository.Impl.EventRepositoryImpl;
import Eventhub.demo.Service.EventService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(EventReqDTO eventReqDTO) {
        if (eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        Event event = new Event();
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventId(UUID.randomUUID());
        return eventRepository.saveEvent(event);
    }

    @Override
    public Event getEventById(UUID eventId) {
        return eventRepository.findById(eventId).orElseThrow(()->new EventNotFoundException("No Event found with the ID : "+eventId));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override
    public Event deleteEvent(UUID eventId) {
        Optional<Event> myEvent =  eventRepository.deleteById(eventId);
        if(myEvent.isEmpty()){
            throw new EventNotFoundException("No Event found with the ID : "+eventId);
        }
        return myEvent.get();
    }

    @Override
    public Event updateEvent(UUID eventId, EventReqDTO eventReqDTO){
        Event event = getEventById(eventId);
        if (!eventReqDTO.getTimeAndDate().equals(event.getDateAndTime()) && eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        eventRepository.saveEvent(event);
        return event;
    }
}
