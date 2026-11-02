package Eventhub.demo.Service.impl;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.BadRequestException;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Repository.EventRepository;
import Eventhub.demo.Service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(EventReqDTO eventReqDTO) {
        if (eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            log.warn("Attempted to create an event with a past date and time: {}",eventReqDTO.getTimeAndDate());
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        Event event = new Event();
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventId(UUID.randomUUID());
        Event responseEvent = eventRepository.saveEvent(event);
        log.info("Event Created Successfully, eventID : {}",responseEvent.getEventId());
        return responseEvent;

    }

    @Override
    public Event getEventById(UUID eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(()->new EventNotFoundException("No Event found with the ID : "+eventId));
        log.info("Event Found Successfully, eventID : {}",event.getEventId());
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override
    public Event deleteEvent(UUID eventId) {
        Optional<Event> myEvent =  eventRepository.deleteById(eventId);
        if(myEvent.isEmpty()){
            log.warn("No event found with the ID : {} to delete", eventId);
            throw new EventNotFoundException("No Event found with the ID : "+eventId);
        }
        Event event = myEvent.get();
        log.info("Event Deleted Successfully, eventID : {}",event.getEventId());
        return event;
    }

    @Override
    public Event updateEvent(UUID eventId, EventReqDTO eventReqDTO){
        log.info("Updating Event, EventID : {} ", eventId);
        Event event = getEventById(eventId);
        if (!eventReqDTO.getTimeAndDate().equals(event.getDateAndTime()) && eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            log.warn("UPDATE_EVENT , Event time and date cannot be in the past. Provided time: {}", eventReqDTO.getTimeAndDate());
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        eventRepository.saveEvent(event);
        log.info("Event Updated Successfully, eventID : {}",event.getEventId());
        return event;
    }
}
