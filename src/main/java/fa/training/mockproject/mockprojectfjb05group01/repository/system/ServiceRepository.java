package fa.training.mockproject.mockprojectfjb05group01.repository.system;


import fa.training.mockproject.mockprojectfjb05group01.entity.system.ServiceHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceHotel,Long> {
    @Query("select s from ServiceHotel s where s.id = :id")
    ServiceHotel getServiceHotelById(@Param("id") Long id);
}
