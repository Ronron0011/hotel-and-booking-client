package fa.training.mockproject.mockprojectfjb05group01.dto.booking;

import fa.training.mockproject.mockprojectfjb05group01.model.enums.BookedRoomStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
public class BookedRoomDTO {
    private Long bookedRoomId;
    private Double amount;
    private BookedRoomStatus bookedRoomStatus;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Boolean isCheckedIn;
    private Boolean isDeleted;
    private String note;
    private Double price;
    private Long bookingId;
    private Long roomId;
}
