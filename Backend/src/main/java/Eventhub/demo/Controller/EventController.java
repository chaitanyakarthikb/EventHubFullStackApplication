package Eventhub.demo.Controller;

import Eventhub.demo.DTO.EventReqDTO;
import Eventhub.demo.Entity.Event;
import Eventhub.demo.Exception.BadRequestException;
import Eventhub.demo.Exception.EventNotFoundException;
import Eventhub.demo.Service.impl.EventServiceImpl;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventServiceImpl eventServiceImpl;

    public EventController(EventServiceImpl eventServiceImpl){
        this.eventServiceImpl = eventServiceImpl;
    }

    @PostMapping("/post")
    public ResponseEntity<Map<String,Object>> saveEvent(@RequestBody EventReqDTO eventReqDTO){
        try{
            Map<String,Object> response = new HashMap<>();
            response.put("apiStatus","Success");
            response.put("event",eventServiceImpl.saveEvent(eventReqDTO));
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(201));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findById(@PathVariable("id")UUID id){
        try{
            Event event = eventServiceImpl.findById(id);
            Map<String,Object> response = new HashMap<>();
            response.put("apiStatus","Success");
            response.put("event",event);
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            throw new BadRequestException("Something went wrong in findById");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteById(@PathVariable("id") UUID id){
        try{
            Map<String,Object> response = new HashMap<>();
            response.put("apiStatus","Success");
            response.put("event",eventServiceImpl.deleteById(id));
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            throw new BadRequestException("Something went wrong in deleteById");
        }

    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> findAllEvents(){
        try{
            Map<String,Object> response = new HashMap<>();
            response.put("apiStatus","Success");
            response.put("events",eventServiceImpl.findAllEvents());
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
        }catch (Exception e){
            throw new BadRequestException("Something went wrong in findAllEvents");
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String,Object>> updateEvent(@PathVariable("id") UUID id, @RequestBody EventReqDTO eventReqDTO){
//        try{
//            Map<String,Object> response = new HashMap<>();
//            response.put("apiStatus","Success");
//            response.put("event",eventServiceImpl.);
//            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
//        } catch (Exception e) {
//            throw new BadRequestException("Something went wrong in deleteById");
//        }
//
//    }


}
