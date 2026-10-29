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

    public EventRepositoryImpl eventRepository;

    @Override
    public Event saveEvent(EventReqDTO eventReqDTO) {
        Event event = new Event();
        event.setEventName(eventReqDTO.getEventName());
        event.setVenueId(eventReqDTO.getVenueId());
        event.setDateAndTime(eventReqDTO.getTimeAndDate());
        event.setEventCapacity(eventReqDTO.getEventCapacity());
        event.setEventId(UUID.randomUUID());
        eventRepository.saveEvent(event);
        return event;
    }

    @Override
    public Event findById(UUID eventId) {
        if (eventRepository.findById(eventId).isPresent()){
            return eventRepository.findById(eventId).get();
        }
        throw new EventNotFoundException("No Event Found with the specified id : " + eventId);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override
    public Optional<Event> deleteById(UUID eventId) {
        Event event  = eventRepository.findById(eventId).isPresent() ? eventRepository.findById(eventId).get() : null;
        if(event == null){
            throw new EventNotFoundException("No Event Found with the specified id : " + eventId);
        }
        Optional<Event> myEvent =  eventRepository.deleteById(eventId);
        return Optional.ofNullable(myEvent).orElseThrow(()->new EventNotFoundException("NO Event Found with the specified id : " + eventId));
    }
}
