package fa.training.mockproject.mockprojectfjb05group01.service.booking.impl;

import fa.training.mockproject.mockprojectfjb05group01.repository.booking.UsedServiceRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.booking.UsedServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsedServiceServiceImpl implements UsedServiceService {
    private final UsedServiceRepository usedServiceRepository;

    @Autowired
    public UsedServiceServiceImpl(UsedServiceRepository usedServiceRepository) {
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public long countTotalUsedService() {
        return usedServiceRepository.countTotalUsedService();
    }
}
