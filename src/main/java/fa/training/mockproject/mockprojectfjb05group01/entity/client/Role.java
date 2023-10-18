package fa.training.mockproject.mockprojectfjb05group01.entity.client;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_info")
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "description", length = 300)
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<Client> clients;
}
