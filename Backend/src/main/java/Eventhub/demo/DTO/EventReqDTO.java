package Eventhub.demo.DTO;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class EventReqDTO {
    private Instant timeAndDate;
    private String eventName;
    private int eventCapacity;
    private UUID venueId;
}