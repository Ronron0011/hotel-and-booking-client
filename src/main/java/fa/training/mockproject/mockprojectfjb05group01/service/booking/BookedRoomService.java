package fa.training.mockproject.mockprojectfjb05group01.service.booking;

import fa.training.mockproject.mockprojectfjb05group01.dto.booking.BookedRoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.BookedRoomStatus;

import java.time.LocalDate;
import java.util.List;

public interface BookedRoomService {
    List<BookedRoomDTO> getAllBookedRoom();
//    List<BookedRoomDTO> getAllBookedRoomByStatus(BookedRoomStatus bookedRoomStatus);
    List<BookedRoom> getAllBookedRoomByStatus(BookedRoomStatus bookedRoomStatus);
    void createBookedRoom(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    void updateBookedRoom(BookedRoom bookedRoom);

    void deleteBookedRoomById(Long id);

    BookedRoomDTO getBookedRoomById(Long id);

    BookedRoomDTO convertEntityToDto(BookedRoom bookedRoom);

    BookedRoom convertDtoToEntity(BookedRoomDTO bookedRoomData);
}
