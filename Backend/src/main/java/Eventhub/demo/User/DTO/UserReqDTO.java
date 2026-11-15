package Eventhub.demo.User.DTO;
import Eventhub.demo.User.Entity.User;
import lombok.Getter;


@Getter
public class UserReqDTO {
    private String fullName;
    private String email;
    private String passwordHash;
    private User.UserRoles role;
}
