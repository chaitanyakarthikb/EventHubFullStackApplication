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
            log.error("Attempted to create an event with a past date and time: {}",eventReqDTO.getTimeAndDate());
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        log.info("Received request at EventService with the following details , venueId {} and eventName {} " ,eventReqDTO.getVenueId(), eventReqDTO.getEventName());
        Event event = new Event();
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventId(UUID.randomUUID());
        log.info("Event Creation Processing is done at the service layer, sent to Repository Layer with eventID {}",event.getEventId());
        return eventRepository.saveEvent(event);
    }

    @Override
    public Event getEventById(UUID eventId) {
        log.info("Received request at EventService to find event with ID: {}", eventId);
        return eventRepository.findById(eventId).orElseThrow(()->new EventNotFoundException("No Event found with the ID : "+eventId));
    }

    @Override
    public List<Event> getAllEvents() {
        log.info("Received request at EventService to find all events");
        return eventRepository.findAllEvents();
    }

    @Override
    public Event deleteEvent(UUID eventId) {
        log.info("Received request at EventService to delete event with ID : {}", eventId);
        Optional<Event> myEvent =  eventRepository.deleteById(eventId);
        if(myEvent.isEmpty()){
            log.error("No event found with the ID : {} to delete", eventId);
            throw new EventNotFoundException("No Event found with the ID : "+eventId);
        }
        return myEvent.get();
    }

    @Override
    public Event updateEvent(UUID eventId, EventReqDTO eventReqDTO){
        log.info("Received request at EventService to update event with ID : {} with following details : venueId : {}, eventName : {}, eventCapacity : {}, timeAndDate : {} ", eventId,eventReqDTO.getVenueId(),eventReqDTO.getEventName(),eventReqDTO.getEventCapacity(),eventReqDTO.getTimeAndDate());
        Event event = getEventById(eventId);
        if (!eventReqDTO.getTimeAndDate().equals(event.getDateAndTime()) && eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            log.error("UPDATE_EVENT , Event time and date cannot be in the past. Provided time: {}", eventReqDTO.getTimeAndDate());
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        log.info("Event Update Processing is done at the service layer, sent to Repository Layer with eventID {}",event.getEventId());
        eventRepository.saveEvent(event);
        return event;
    }
}
