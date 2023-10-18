package fa.training.mockproject.mockprojectfjb05group01.service.booking.impl;

import fa.training.mockproject.mockprojectfjb05group01.dto.booking.BookedRoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.system.RoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Room;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.BookedRoomStatus;
import fa.training.mockproject.mockprojectfjb05group01.repository.booking.BookedRoomRepository;
import fa.training.mockproject.mockprojectfjb05group01.repository.system.RoomRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.booking.BookedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookedRoomServiceImpl implements BookedRoomService {
    private final BookedRoomRepository bookedRoomRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookedRoomServiceImpl(BookedRoomRepository bookedRoomRepository, RoomRepository roomRepository) {
        this.bookedRoomRepository = bookedRoomRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<BookedRoomDTO> getAllBookedRoom() {
        List<BookedRoom> bookedRooms = bookedRoomRepository.findAll();
        return bookedRooms.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<BookedRoomDTO> getAllBookedRoomByStatus(BookedRoomStatus bookedRoomStatus) {
//        List<BookedRoom> bookedRoomList = bookedRoomRepository.getBookedRoomsByStatus(bookedRoomStatus);
//        return bookedRoomList.stream()
//                .map(this::convertEntityToDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<BookedRoom> getAllBookedRoomByStatus(BookedRoomStatus bookedRoomStatus) {
        return bookedRoomRepository.getBookedRoomsByStatus(bookedRoomStatus);
    }

    @Override
    public void createBookedRoom(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        Room room = roomRepository.getReferenceById(roomId);
        BookedRoom bookedRoom1 = new BookedRoom();
        bookedRoom1.setCheckIn(checkInDate);
        bookedRoom1.setCheckOut(checkOutDate);
        bookedRoom1.setPrice(room.getPrice());
        bookedRoom1.setBookedRoomStatus(BookedRoomStatus.WAIT);
        bookedRoom1.setIsCheckedIn(false);
        bookedRoom1.setIsDeleted(false);
        bookedRoom1.setRoom(room);
        bookedRoomRepository.save(bookedRoom1);
    }

    @Override
    public void updateBookedRoom(BookedRoom bookedRoom) {
        bookedRoomRepository.save(bookedRoom);
    }

    @Override
    public void deleteBookedRoomById(Long id) {
        bookedRoomRepository.deleteById(id);
    }

    @Override
    public BookedRoomDTO getBookedRoomById(Long id) {
        BookedRoom bookedRoom = bookedRoomRepository.getReferenceById(id);
        return convertEntityToDto(bookedRoom);
    }

    @Override
    public BookedRoomDTO convertEntityToDto(BookedRoom bookedRoom) {
        BookedRoomDTO bookedRoomData = new BookedRoomDTO();
        bookedRoomData.setBookedRoomId(bookedRoom.getId());
        bookedRoomData.setBookingId(bookedRoomData.getBookingId());
        bookedRoomData.setRoomId(bookedRoomData.getRoomId());
        bookedRoomData.setCheckIn(bookedRoomData.getCheckIn());
        bookedRoomData.setCheckOut(bookedRoomData.getCheckOut());
        bookedRoomData.setPrice(bookedRoom.getPrice());
        bookedRoomData.setNote(bookedRoom.getNote());
        bookedRoomData.setAmount(bookedRoom.getAmount());
        bookedRoomData.setIsCheckedIn(bookedRoom.getIsCheckedIn());
        bookedRoomData.setBookedRoomStatus(bookedRoomData.getBookedRoomStatus());
        bookedRoomData.setIsDeleted(bookedRoomData.getIsDeleted());
        return bookedRoomData;
    }

    @Override
    public BookedRoom convertDtoToEntity(BookedRoomDTO bookedRoomData) {
        BookedRoom bookedRoom = new BookedRoom();
        bookedRoom.setId(bookedRoomData.getBookedRoomId());
        bookedRoom.getBooking().setId(bookedRoomData.getBookingId());
        bookedRoom.getRoom().setId(bookedRoomData.getRoomId());
        bookedRoom.setCheckIn(bookedRoomData.getCheckIn());
        bookedRoom.setCheckOut(bookedRoomData.getCheckOut());
        bookedRoom.setPrice(bookedRoomData.getPrice());
        bookedRoom.setNote(bookedRoomData.getNote());
        bookedRoom.setIsCheckedIn(bookedRoomData.getIsCheckedIn());
        bookedRoom.setBookedRoomStatus(bookedRoomData.getBookedRoomStatus());
        bookedRoom.setIsDeleted(bookedRoomData.getIsDeleted());
        return bookedRoom;
    }
}
