package Eventhub.demo.Service.Event;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EventService {
    Event createEvent(EventReqDTO eventReqDTO);
    Event findOneById(UUID eventId);
    List<Event> findAllEvents();
    Event deleteById(UUID eventId);

}
