package Eventhub.demo.User.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class LoginReqUserDTO {
    @NotBlank
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank
    @Length(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
