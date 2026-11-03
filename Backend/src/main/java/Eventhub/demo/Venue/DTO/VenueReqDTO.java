package Eventhub.demo.Venue.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class VenueReqDTO {
    @Setter
    @Size(min=5,message = "VenueName must be at least 3 characters long")
    @Length(max = 100, message = "Venue name must not exceed 100 characters")
    private String venueName;
    @Setter
    @Length(max = 200, message = "Address must not exceed 200 characters")
    @Size(min=5,message = "Address must be at least 3 characters long")
    private String address;
    @Setter
    @Min(value = 0, message = "Max capacity must be a positive integer")
    private int maxCapacity;
}
