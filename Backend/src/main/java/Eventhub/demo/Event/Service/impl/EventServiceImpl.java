package Eventhub.demo.Event.Service.impl;

import Eventhub.demo.Event.DTO.EventReqDTO;
import Eventhub.demo.Event.Entity.Event;
import Eventhub.demo.Common.Exception.BadRequestException;
import Eventhub.demo.Common.Exception.EventNotFoundException;
import Eventhub.demo.Event.Repository.EventRepository;
import Eventhub.demo.Event.Service.EventService;
import Eventhub.demo.Venue.Entity.Venue;
import Eventhub.demo.Venue.Service.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final VenueService venueService;

    public EventServiceImpl(EventRepository eventRepository, VenueService venueService){
        this.eventRepository = eventRepository;
        this.venueService = venueService;
    }

    @Override
    public Event createEvent(EventReqDTO eventReqDTO) {
        if (eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            log.warn("Attempted to create an event with a past date and time: {}",eventReqDTO.getTimeAndDate());
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        Event event = new Event();
        Venue myVenue = venueService.getVenueById(eventReqDTO.getVenueId());
        event.setEventName(eventReqDTO.getEventName());
        event.setVenue(myVenue);
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        Event responseEvent = eventRepository.save(event);
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
        return eventRepository.findAll();
    }

    @Override
    public Event deleteEvent(UUID eventId) {
        Event myEvent =  getEventById(eventId);
        eventRepository.delete(myEvent);
        log.info("Event Deleted Successfully, eventID : {}",myEvent.getEventId());
        return myEvent;
    }

    @Override
    public Event updateEvent(UUID eventId, EventReqDTO eventReqDTO){
        log.info("Updating Event, EventID : {} ", eventId);
        Event event = getEventById(eventId);
        if (!eventReqDTO.getTimeAndDate().equals(event.getDateAndTime()) && eventReqDTO.getTimeAndDate().isBefore(Instant.now())) {
            log.warn("UPDATE_EVENT , Event time and date cannot be in the past. Provided time: {}", eventReqDTO.getTimeAndDate());
            throw new BadRequestException("Event time and date cannot be in the past.");
        }
        Venue myVenue = venueService.getVenueById(eventReqDTO.getVenueId());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventName(eventReqDTO.getEventName());
        event.setVenue(myVenue);
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        eventRepository.save(event);
        log.info("Event Updated Successfully, eventID : {}",event.getEventId());
        return event;
    }
}
