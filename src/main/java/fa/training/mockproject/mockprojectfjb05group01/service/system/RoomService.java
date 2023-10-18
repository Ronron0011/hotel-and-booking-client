package fa.training.mockproject.mockprojectfjb05group01.service.system;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.RoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<RoomDTO> getAllRoom();

    void createRoom(RoomDTO room);

    void updateRoom(RoomDTO room);

    void deleteById(Long id);

    RoomDTO getRoomById(Long id);

    Room convertDtoToEntity(RoomDTO data);

    RoomDTO convertEntityToDto(Room room);

    List<RoomDTO> listRoomsAvailableByHotelId(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate);

}
