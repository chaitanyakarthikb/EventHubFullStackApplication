package Eventhub.demo.Event.Entity;

import Eventhub.demo.Venue.Entity.Venue;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    @Setter
    @ManyToOne
    @JoinColumn(name = "venueId")
    private Venue venue;
    @Setter
    private String eventName;
    @Setter
    private int eventCapacity;
    @Setter
    private Instant dateAndTime;
}
