package fa.training.mockproject.mockprojectfjb05group01.service.system.impl;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.RoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Hotel;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Room;
import fa.training.mockproject.mockprojectfjb05group01.repository.booking.BookedRoomRepository;
import fa.training.mockproject.mockprojectfjb05group01.repository.system.HotelRepository;
import fa.training.mockproject.mockprojectfjb05group01.repository.system.RoomRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.system.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    private final BookedRoomRepository bookedRoomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository, BookedRoomRepository bookedRoomRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.bookedRoomRepository = bookedRoomRepository;
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createRoom(RoomDTO room) {
        roomRepository.save(convertDtoToEntity(room));
    }

    @Override
    public void updateRoom(RoomDTO room) {
        roomRepository.save(convertDtoToEntity(room));
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        return convertEntityToDto(roomRepository.getReferenceById(id));
    }

    @Override
    public Room convertDtoToEntity(RoomDTO data) {
        Room room = new Room();
        room.setId(data.getId());
        room.setName(data.getName());
        room.setType(data.getType());
        room.setIsMaintained(data.getIsMaintained());
        room.setPrice(data.getPrice());
        room.setDescription(data.getDescription());
        room.setMaxCapacity(data.getMaxCapacity());
        room.setHotel(data.getHotel());
        return room;
    }

    @Override
    public RoomDTO convertEntityToDto(Room room) {
        RoomDTO data = new RoomDTO();
        data.setId(room.getId());
        data.setName(room.getName());
        data.setType(room.getType());
        data.setIsMaintained(room.getIsMaintained());
        data.setPrice(room.getPrice());
        data.setDescription(room.getDescription());
        data.setMaxCapacity(room.getMaxCapacity());
        data.setHotel(room.getHotel());
        return data;
    }

    @Override
    public List<RoomDTO> listRoomsAvailableByHotelId(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate) {
        List<BookedRoom> bookedRoomList = bookedRoomRepository.findBookedRoomsByHotelAndDateRange(hotelId, checkinDate, checkoutDate);
        Hotel hotel = hotelRepository.getReferenceById(hotelId);
        List<Room> totalRoomOfHotel = hotel.getRooms();
        return totalRoomOfHotel.stream()
                .filter(room -> !bookedRoomList.stream()
                        .anyMatch(bookedRoom -> room.getId().equals(bookedRoom.getRoom().getId()) || Boolean.TRUE.equals(room.getIsMaintained())))
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
