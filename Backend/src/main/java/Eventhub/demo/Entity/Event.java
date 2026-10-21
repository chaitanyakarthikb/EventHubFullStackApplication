package Eventhub.demo.Entity;

import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
    @EqualsAndHashCode.Include
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
