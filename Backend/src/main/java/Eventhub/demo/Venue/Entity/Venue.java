package Eventhub.demo.Venue.Entity;

import Eventhub.demo.Event.Entity.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID venueId;
    @Setter
    private String venueName;
    @Setter
    private String address;
    @Setter
    private int maxCapacity;
    @JsonIgnore 
    @OneToMany(mappedBy = "venue")
    private List<Event> events;
}
