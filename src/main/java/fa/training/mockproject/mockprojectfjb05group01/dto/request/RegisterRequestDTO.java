package fa.training.mockproject.mockprojectfjb05group01.dto.request;

import fa.training.mockproject.mockprojectfjb05group01.utilize.email.EmailConstraint;
import fa.training.mockproject.mockprojectfjb05group01.utilize.password.PasswordConstraint;
import fa.training.mockproject.mockprojectfjb05group01.utilize.phone.PhoneConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotEmpty(message = "First name must not be empty.")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty.")
    private String lastName;

    @EmailConstraint
    private String email;

    @PasswordConstraint
    private String password;

    private String rePassword;

    @PhoneConstraint
    private String phone;
}
