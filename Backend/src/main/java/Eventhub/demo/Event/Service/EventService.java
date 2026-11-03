package Eventhub.demo.Event.Service;

import Eventhub.demo.Event.DTO.EventReqDTO;
import Eventhub.demo.Event.Entity.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    Event createEvent(EventReqDTO eventReqDTO);
    Event getEventById(UUID eventId);
    List<Event> getAllEvents();
    Event deleteEvent(UUID eventId);
    Event updateEvent(UUID eventId, EventReqDTO eventReqDTO);
}
