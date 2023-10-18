package fa.training.mockproject.mockprojectfjb05group01.entity.client;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_info")
public class Client extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "client_code")
    private String clientCode;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email" ,length = 254)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password", length = 254)
    private String password;

    @Column(name = "activation_status", columnDefinition = "BIT(1) NULL DEFAULT TRUE")
    private Boolean activationStatus;

    @Column(name = "email_verification_status", columnDefinition = "BIT(1) NULL DEFAULT FALSE")
    private Boolean emailVerificationStatus;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
