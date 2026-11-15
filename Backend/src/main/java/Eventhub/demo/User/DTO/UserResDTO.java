package Eventhub.demo.User.DTO;

import Eventhub.demo.User.Entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Builder
@Getter
public class UserResDTO {
    private UUID userId;
    private String fullName;
    private String email;
    private User.UserRoles role;
    private Instant createdAt;
}
