package Eventhub.demo.Venue.Controller;

import Eventhub.demo.Venue.DTO.VenueReqDTO;
import Eventhub.demo.Venue.Entity.Venue;
import Eventhub.demo.Venue.Service.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Slf4j
@RequestMapping("/api/v1/venues")
@RestController
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<Venue>>getAllVenues(){
        log.info("Request received to getAllVenues");
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(venueService.getAllVenues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable UUID id){
        log.info("Request received to getVenueById {} ", id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(venueService.getVenueById(id));
    }

    @PostMapping()
    public ResponseEntity<Venue> createVenue(@RequestBody VenueReqDTO venueReqDTO){
        log.info("Request received to createVenue {} ", venueReqDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(venueService.createVenue(venueReqDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Venue> deleteVenue(@PathVariable UUID id){
        log.info("Request received to deleteVenue {} ", id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(venueService.deleteVenue(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable UUID id, @RequestBody VenueReqDTO venueReqDTO){
        log.info("Request received to updateVenue {} ", id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(venueService.updateVenue(id,venueReqDTO));
    }

}
