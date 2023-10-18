package fa.training.mockproject.mockprojectfjb05group01.repository.booking;

import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.BookedRoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
    @Query("SELECT br FROM BookedRoom br " +
            "WHERE br.room.hotel.id = :hotelId " +
            "AND ((br.checkIn <= :checkoutDate AND br.checkOut >= :checkinDate) " +
            "OR (br.checkIn BETWEEN :checkinDate AND :checkoutDate) " +
            "OR (br.checkOut BETWEEN :checkinDate AND :checkoutDate))")
    List<BookedRoom> findBookedRoomsByHotelAndDateRange(
            @Param("hotelId") Long hotelId,
            @Param("checkinDate") LocalDate checkinDate,
            @Param("checkoutDate") LocalDate checkoutDate
    );
    @Query("SELECT br FROM BookedRoom br WHERE br.bookedRoomStatus = ?1")
    List<BookedRoom> getBookedRoomsByStatus(BookedRoomStatus bookedRoomStatus);
}
