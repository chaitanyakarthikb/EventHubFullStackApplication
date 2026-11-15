package Eventhub.demo.User.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    public enum UserRoles{
        ADMIN,
        USER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID userId;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    private UserRoles role;
    private Instant createdAt;
}
