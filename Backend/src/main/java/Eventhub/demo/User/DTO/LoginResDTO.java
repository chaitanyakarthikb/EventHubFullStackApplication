package Eventhub.demo.User.DTO;

import Eventhub.demo.User.Entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class LoginResDTO {
    private User user;
    private String token;

}
