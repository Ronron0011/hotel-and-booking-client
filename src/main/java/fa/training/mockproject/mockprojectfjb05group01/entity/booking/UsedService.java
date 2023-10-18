package fa.training.mockproject.mockprojectfjb05group01.entity.booking;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.ServiceHotel;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "used_service_info")
public class UsedService extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_service_id")
    private Long id;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "booked_room_id")
    private BookedRoom bookedRoom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private ServiceHotel serviceHotel;

}
