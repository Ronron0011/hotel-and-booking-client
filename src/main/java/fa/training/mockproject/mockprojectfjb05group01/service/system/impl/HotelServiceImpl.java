package fa.training.mockproject.mockprojectfjb05group01.service.system.impl;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.HotelDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Hotel;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Room;
import fa.training.mockproject.mockprojectfjb05group01.repository.booking.BookedRoomRepository;
import fa.training.mockproject.mockprojectfjb05group01.repository.system.HotelRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.system.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final BookedRoomRepository bookedRoomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, BookedRoomRepository bookedRoomRepository) {
        this.hotelRepository = hotelRepository;
        this.bookedRoomRepository = bookedRoomRepository;

    }

    @Override
    public List<HotelDTO> getAllHotel() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelById(Long hotelId) {
        return  convertEntityToDto(hotelRepository.getReferenceById(hotelId));
    }

    @Override
    public HotelDTO convertEntityToDto(Hotel hotel) {
        HotelDTO data = new HotelDTO();
        data.setId(hotel.getId());
        data.setName(hotel.getName());
        data.setType(hotel.getType());
        data.setStarLevel(hotel.getStarLevel());
        data.setStatus(hotel.getStatus());
        data.setDescription(hotel.getDescription());
        data.setAddress(hotel.getAddress());
        data.setCity(hotel.getCity());
        data.setCountry(hotel.getCountry());
        return data;
    }

    @Override
    public Hotel convertDtoToEntity(HotelDTO hotelData) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelData.getId());
        hotel.setName(hotelData.getName());
        hotel.setType(hotelData.getType());
        hotel.setStarLevel(hotelData.getStarLevel());
        hotel.setStatus(hotelData.getStatus());
        hotel.setDescription(hotelData.getDescription());
        hotel.setAddress(hotelData.getAddress());
        hotel.setCity(hotelData.getCity());
        hotel.setCountry(hotelData.getCountry());
        return null;
    }

    // search
    public List<HotelDTO> searchHotels(String keyword,
                                       String filter,
                                       LocalDate checkinDate,
                                       LocalDate checkoutDate,
                                       Integer numberOfPeople,
                                       Integer numberOfRooms) {
        List<HotelDTO> hotelData = new ArrayList<>();
        List<Hotel> hotels = searchHotelsByKeyword(keyword, filter);
        hotels.stream()
                .filter(hotel -> hasEnoughRooms(hotel, numberOfRooms, numberOfPeople, checkinDate, checkoutDate))
                .map(this::convertEntityToDto)
                .forEach(hotelData::add);
        return hotelData;
    }

    private List<Hotel> searchHotelsByKeyword(String keyword,
                                              String filter) {
        List<Hotel> hotels = new ArrayList<>();
        if (Objects.equals(filter, "address")) {
            hotels = hotelRepository.findHotelByAddress(keyword);
        }

        if (Objects.equals(filter, "hotelName")) {
            hotels = hotelRepository.findHotelsByHotelName(keyword);
        }

        if (Objects.equals(filter, "city")) {
            hotels = hotelRepository.findHotelsByCity(keyword);
        }

        if (Objects.equals(filter, "country")) {
            hotels = hotelRepository.findHotelsByCountry(keyword);
        }
        return hotels;
    }

    private boolean hasEnoughRooms(Hotel hotel,
                                   Integer numberOfRooms,
                                   Integer numberOfPeople,
                                   LocalDate checkinDate,
                                   LocalDate checkoutDate) {
        List<BookedRoom> bookedRooms = bookedRoomRepository.findBookedRoomsByHotelAndDateRange(hotel.getId(), checkinDate, checkoutDate);
        int hotelMaxCapacity = 0;
        int hotelUsedCapacity = 0;
        for (Room room: hotel.getRooms()){
            hotelMaxCapacity += room.getMaxCapacity();
        }
        int busyRooms = bookedRooms.size();
        for (BookedRoom bookedRoom : bookedRooms) {
            hotelUsedCapacity += bookedRoom.getRoom().getMaxCapacity();
        }
        int availableRooms = hotel.getRooms().size() - busyRooms;
        int availableCapacity = hotelMaxCapacity - hotelUsedCapacity;
        return (availableRooms >= numberOfRooms) && (availableCapacity >= numberOfPeople);
    }
}
