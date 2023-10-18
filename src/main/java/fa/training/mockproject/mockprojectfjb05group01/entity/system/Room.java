package fa.training.mockproject.mockprojectfjb05group01.entity.system;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_info")
public class Room extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "room_name", nullable = false, length = 100)
    private String name;

    @Column(name = "room_type", length = 100)
    private String type;

    @Column(name = "price", columnDefinition = "DECIMAL(9,2) default 0.00")
    private Double price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_maintained", columnDefinition = "BIT DEFAULT FALSE")
    private Boolean isMaintained;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<BookedRoom> bookedRooms;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
