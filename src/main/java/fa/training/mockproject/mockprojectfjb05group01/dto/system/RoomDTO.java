package fa.training.mockproject.mockprojectfjb05group01.dto.system;

import fa.training.mockproject.mockprojectfjb05group01.entity.system.Hotel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoomDTO {
    private Long id;
    private String name;
    private String type;
    private double price;
    private String description;
    private Boolean isMaintained;
    private int maxCapacity;
    private Hotel hotel;
}
