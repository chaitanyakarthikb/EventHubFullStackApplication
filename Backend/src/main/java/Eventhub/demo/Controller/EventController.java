package Eventhub.demo.Controller;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import Eventhub.demo.Service.EventService;
import Eventhub.demo.Service.impl.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> saveEvent(@Valid @RequestBody EventReqDTO eventReqDTO){
        log.info("Received saveEvent request at EventController with the following details , venueId {} and eventName {} " ,eventReqDTO.getVenueId(), eventReqDTO.getEventName());
        Map<String,Object> response = new HashMap<>();
        response.put("apiStatus","Success");
        response.put("event",eventService.createEvent(eventReqDTO));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findById(@PathVariable("id")UUID id){
        log.info("Received findById request at EventController with eventId {} " ,id);
        Event event = eventService.getEventById(id);
        Map<String,Object> response = new HashMap<>();
        response.put("apiStatus","Success");
        response.put("event",event);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteById(@PathVariable("id") UUID id){
        log.info("Received deleteById request at EventController with eventId {} " ,id);
        Map<String,Object> response = new HashMap<>();
        response.put("apiStatus","Success");
        response.put("event",eventService.deleteEvent(id));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAllEvents(){
        log.info("Received findAllEvents request at EventController");
        Map<String,Object> response = new HashMap<>();
        response.put("apiStatus","Success");
        response.put("events",eventService.getAllEvents());
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateEvent(@PathVariable("id") UUID id,@Valid @RequestBody EventReqDTO eventReqDTO){
        log.info("Received updateEvent request at EventController with eventId {} and eventName {} " ,id, eventReqDTO.getEventName());
        Map<String,Object> response = new HashMap<>();
        response.put("apiStatus","Success");
        response.put("event",eventService.updateEvent(id,eventReqDTO));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }
}
