package fa.training.mockproject.mockprojectfjb05group01.repository.system;

import fa.training.mockproject.mockprojectfjb05group01.entity.system.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h " +
            "WHERE h.name LIKE %:keyword% " +
            "AND h.status = 'ACCEPTED'")
    List<Hotel> findHotelsByHotelName(@Param("keyword")String keyword);

    @Query("SELECT h FROM Hotel h " +
            "WHERE h.address LIKE %:keyword% " +
            "AND h.status = 'ACCEPTED'")
    List<Hotel> findHotelByAddress(@Param("keyword")String keyword);

    @Query("SELECT h FROM Hotel h " +
            "WHERE h.city LIKE %:keyword% " +
            "AND h.status = 'ACCEPTED' ")
    List<Hotel> findHotelsByCity(@Param("keyword")String keyword);

    @Query("SELECT h FROM Hotel h " +
            "WHERE h.country LIKE %:keyword% " +
            "AND h.status = 'ACCEPTED' ")
    List<Hotel> findHotelsByCountry(@Param("keyword")String keyword);
}
