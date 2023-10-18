package fa.training.mockproject.mockprojectfjb05group01.entity.booking;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Room;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.BookedRoomStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booked_room_info")
public class BookedRoom extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booked_room_id")
    private Long id;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @Column(name = "price")
    private Double price;

    @Column(name = "note")
    private String note;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "is_checked_in", columnDefinition = "BIT DEFAULT FALSE")
    private Boolean isCheckedIn;

    @Column(name = "booked_room_status")
    @Enumerated(value = EnumType.STRING)
    private BookedRoomStatus bookedRoomStatus;

    @Column(name = "is_deleted", columnDefinition = "BIT DEFAULT FALSE")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "bookedRoom")
    private List<UsedService> usedServices;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
