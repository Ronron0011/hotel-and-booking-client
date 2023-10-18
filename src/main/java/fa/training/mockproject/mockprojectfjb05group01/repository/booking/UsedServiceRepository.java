package fa.training.mockproject.mockprojectfjb05group01.repository.booking;

import fa.training.mockproject.mockprojectfjb05group01.entity.booking.UsedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedServiceRepository extends JpaRepository<UsedService,Integer> {
    @Query("SELECT COUNT(u.id) FROM UsedService u")
    long countTotalUsedService();
}

