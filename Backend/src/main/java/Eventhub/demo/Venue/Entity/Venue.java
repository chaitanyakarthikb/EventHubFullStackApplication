package Eventhub.demo.Venue.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private UUID venueId;
    private String venueName;
    private String address;
    private int maxCapacity;
}
