package fa.training.mockproject.mockprojectfjb05group01.dto.system;

import fa.training.mockproject.mockprojectfjb05group01.model.enums.HotelStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class HotelDTO {
    private Long id;

    private String name;

    private String type;

    private int starLevel;

    @Enumerated(EnumType.STRING)
    private HotelStatus status;

    private String address;

    private String description;

    private String city;

    private String country;
}
