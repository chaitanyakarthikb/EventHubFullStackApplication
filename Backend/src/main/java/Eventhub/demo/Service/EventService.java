package Eventhub.demo.Service;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface EventService {
    Event saveEvent(EventReqDTO eventReqDTO);
    Event findById(UUID eventId);
    List<Event> findAllEvents();
    Optional<Event> deleteById(UUID eventId);
    Event updateEvent(UUID eventId, EventReqDTO eventReqDTO);
}
