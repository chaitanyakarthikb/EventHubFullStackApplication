package Eventhub.demo.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.UUID;

@Getter
public class EventReqDTO {
    @NotNull(message = "Date and Time cannot be null")
    private Instant timeAndDate;
    @Length(min = 3)
    @NotBlank
    private String eventName;
    @Min(value = 1, message = "Event capacity must be greater than 0")
    private int eventCapacity;
    @NotNull(message = "Venue ID cannot be null")
    private UUID venueId;
}