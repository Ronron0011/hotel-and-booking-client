package fa.training.mockproject.mockprojectfjb05group01.service.system.impl;



import fa.training.mockproject.mockprojectfjb05group01.dto.system.ServiceHotelDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.ServiceHotel;
import fa.training.mockproject.mockprojectfjb05group01.repository.system.ServiceRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.system.ServiceHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceHotelServiceImpl implements ServiceHotelService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceHotelServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceHotelDTO> getAllServiceHotel() {
        List<ServiceHotel> serviceHotels = serviceRepository.findAll();
        return serviceHotels.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createServiceHotel(ServiceHotelDTO serviceHotelData) {
           serviceRepository.save(convertDtoToEntity(serviceHotelData));
    }

    @Override
    public void updateServiceHotel(ServiceHotelDTO serviceHotelData) {
        ServiceHotel existServiceHotel = serviceRepository.findById(serviceHotelData.getServiceId())
                .orElseThrow(() -> new RuntimeException("ServiceHotel not found with  service_id = "+ serviceHotelData.getServiceId()));

        existServiceHotel.setUnity(serviceHotelData.getUnity());
        existServiceHotel.setPrice(serviceHotelData.getPrice());

        serviceRepository.save(existServiceHotel);

    }

    @Override
    public void deleteServiceHotel(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public ServiceHotelDTO getServiceHotelByServiceId(Long id) {
        return convertEntityToDto(serviceRepository.getReferenceById(id));
    }

    @Override
    public ServiceHotel convertDtoToEntity(ServiceHotelDTO serviceHotelData) {
        ServiceHotel serviceHotel = new ServiceHotel();
         serviceHotel.setId(serviceHotelData.getServiceId());
         serviceHotel.setServiceName(serviceHotelData.getServiceName());
         serviceHotel.setUnity(serviceHotelData.getUnity());
         serviceHotel.setPrice(serviceHotelData.getPrice());
        return serviceHotel;
    }

    @Override
    public ServiceHotelDTO convertEntityToDto(ServiceHotel serviceHotel) {
        ServiceHotelDTO serviceHotelData = new ServiceHotelDTO();
        serviceHotelData.setServiceId(serviceHotel.getId());
        serviceHotelData.setServiceName(serviceHotel.getServiceName());
        serviceHotelData.setPrice(serviceHotel.getPrice());
        serviceHotelData.setUnity(serviceHotel.getUnity());
        return serviceHotelData;
    }
}
