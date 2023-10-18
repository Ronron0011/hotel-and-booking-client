package fa.training.mockproject.mockprojectfjb05group01.entity.system;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_hotel_info")
public class ServiceHotel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @Column(name = "service_name", length = 200)
    private String serviceName;

    @Column(name = "unity", length = 100)
    private String unity;

    @Column(name = "price")
    private Double price;

}
