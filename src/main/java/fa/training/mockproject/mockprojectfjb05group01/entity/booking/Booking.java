package fa.training.mockproject.mockprojectfjb05group01.entity.booking;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long id;

    private String bookDate;

    private Double totalAmount;

    private String note;

    @Column(name = "is_cancelled", columnDefinition = "BIT DEFAULT FALSE")
    private Boolean isCancelled;

    @OneToMany(mappedBy = "booking")
    private Set<BookedRoom> bookedRooms;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
