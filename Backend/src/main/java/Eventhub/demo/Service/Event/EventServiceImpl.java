package Eventhub.demo.Service.Event;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(EventReqDTO eventReqDTO) {
        Event eventToSave = new Event().builder()
                .eventCapacity(eventReqDTO.getEventCapacity())
                .eventName(eventReqDTO.getEventName())
                .venueId(eventReqDTO.getVenueId())
                .build();
//        ONCE THE JPA IMPLEMENTATION IS DONE, THIS WILL BE REPLACED WITH A CALL TO THE REPOSITORY'S SAVE METHOD
        EventRepository.events.add(eventToSave);
        return eventToSave;
    }

    @Override
    public Event findOneById(UUID eventId) {
        return EventRepository.events.stream().filter(event1 -> event1.getEventId().equals(eventId)).findFirst().orElse(null);
    }

    @Override
    public List<Event> findAllEvents() {
        return EventRepository.events;
    }

    @Override
    public Event deleteById(UUID eventId) {
        Event eventToDelete = findOneById(eventId);
        if (eventToDelete == null){
        throw new EventNotFoundException("Event with ID " + eventId + " not found.");
        }
        if (eventToDelete!=null){
            EventRepository.events.remove(eventToDelete);
        }
        return eventToDelete;
    }
}
