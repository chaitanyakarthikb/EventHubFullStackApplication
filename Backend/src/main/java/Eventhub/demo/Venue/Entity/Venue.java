package Eventhub.demo.Venue.Entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Venue {
    private UUID venueId;
    private String venueName;
    private String address;
    private int maxCapacity;
}
