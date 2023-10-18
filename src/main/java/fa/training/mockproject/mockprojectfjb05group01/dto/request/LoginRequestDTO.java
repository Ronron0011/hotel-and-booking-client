package fa.training.mockproject.mockprojectfjb05group01.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank(message = "Please input your email")
    private String email;

    @NotBlank(message = "Please input your password")
    public String password;

}
