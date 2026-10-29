package Eventhub.demo.Service;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event createEvent(EventReqDTO eventReqDTO);
    Event getEventById(UUID eventId);
    List<Event> getAllEvents();
    Optional<Event> deleteEvent(UUID eventId);
    Event updateEvent(EventReqDTO eventReqDTO, UUID eventId);
}
