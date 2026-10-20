package Eventhub.demo.Entity;

import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Event {
    private UUID eventId;
    @Setter 
    private UUID venueId;
    @Setter
    private String eventName;
    @Setter
    private int eventCapacity;
    @Setter
    private Instant dateAndTime;
}
