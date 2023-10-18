package fa.training.mockproject.mockprojectfjb05group01.service.system;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.HotelDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    List<HotelDTO> getAllHotel();

    HotelDTO getHotelById(Long hotelId);

    HotelDTO convertEntityToDto(Hotel hotel);

    Hotel convertDtoToEntity(HotelDTO hotelData);

    List<HotelDTO> searchHotels(String keyword, String filter, LocalDate checkinDate, LocalDate checkoutDate, Integer people, Integer numberOfRooms);

}
