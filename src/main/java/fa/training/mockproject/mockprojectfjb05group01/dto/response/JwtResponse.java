package fa.training.mockproject.mockprojectfjb05group01.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer ";
    private  String firstName;
    private String lastName;
    private String email;
    private  String phone;
    private List<String> roles;

    public JwtResponse(String token, String firstName, String lastName, String email, String phone, List<String> roles) {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }
}
