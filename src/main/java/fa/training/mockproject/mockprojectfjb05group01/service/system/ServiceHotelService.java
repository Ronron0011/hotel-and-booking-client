package fa.training.mockproject.mockprojectfjb05group01.service.system;


import fa.training.mockproject.mockprojectfjb05group01.dto.system.ServiceHotelDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.ServiceHotel;

import java.util.List;

public interface ServiceHotelService {
    List<ServiceHotelDTO> getAllServiceHotel();

    void createServiceHotel(ServiceHotelDTO serviceHotelDTO);

    void updateServiceHotel(ServiceHotelDTO serviceHotelDTO);

    void deleteServiceHotel(Long id);

    ServiceHotelDTO getServiceHotelByServiceId(Long id);

    ServiceHotel convertDtoToEntity(ServiceHotelDTO serviceHotelData);

    ServiceHotelDTO convertEntityToDto(ServiceHotel serviceHotel);

}
