package Eventhub.demo.Venue.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueReqDTO {
    @Setter
    private String venueName;
    @Setter
    private String address;
    @Setter
    private int maxCapacity;
}
