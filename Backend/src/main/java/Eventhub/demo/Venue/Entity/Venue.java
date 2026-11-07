package Eventhub.demo.Venue.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID venueId;
    @Setter
    private String venueName;
    @Setter
    private String address;
    @Setter
    private int maxCapacity;
}
