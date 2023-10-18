package fa.training.mockproject.mockprojectfjb05group01.entity.system;

import fa.training.mockproject.mockprojectfjb05group01.entity.BaseModel;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.HotelStatus;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel_info")
public class Hotel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    @Column(name = "hotel_name", unique = true, length = 200)
    private String name;

    @Column(name = "hotel_type", length = 100)
    private String type;

    @Column(name = "hotel_star_level", length = 5)
    private int starLevel;

    @Column(name = "hotel_status")
    @Enumerated(EnumType.STRING)
    private HotelStatus status;

    @Column(name = "address")
    private String address;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;

}
