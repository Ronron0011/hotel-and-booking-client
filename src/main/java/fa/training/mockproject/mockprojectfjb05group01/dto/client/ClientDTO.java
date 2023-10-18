package fa.training.mockproject.mockprojectfjb05group01.dto.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientDTO {
    private Long id;

    private Boolean activationStatus;

    private String clientCode;

    private LocalDate dateOfBirth;

    private String email;

    private Boolean emailVerificationStatus;

    private String firstName;

    private String lastName;

    private String password;

    private String phone;

    private Long roleId;

}
