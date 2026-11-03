package Eventhub.demo.Venue.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class VenueReqDTO {
    @Size(min=5,message = "VenueName must be at least 5 characters long")
    @Length(max = 100, message = "Venue name must not exceed 100 characters")
    @NotBlank(message = "Venue name must be provided")
    private String venueName;
    @Length(max = 200, message = "Address must not exceed 200 characters")
    @Size(min=5,message = "Address must be at least 5 characters long")
    @NotBlank(message = "Address must be provided")
    private String address;
    @NotNull(message = "Max capacity must be provided")
    @Min(value = 10, message = "Min capacity must be a 10")
    private int maxCapacity;
}
