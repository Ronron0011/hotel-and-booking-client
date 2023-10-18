package fa.training.mockproject.mockprojectfjb05group01.controller.booking;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.RoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.service.booking.BookedRoomService;
import fa.training.mockproject.mockprojectfjb05group01.service.system.HotelService;
import fa.training.mockproject.mockprojectfjb05group01.service.system.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/booked-room")
public class BookedRoomController {
    private final BookedRoomService bookedRoomService;

    @Autowired
    public BookedRoomController(BookedRoomService bookedRoomService) {
        this.bookedRoomService = bookedRoomService;
    }

    @GetMapping("")
    public String bookedRoom(@RequestParam(name = "keyword") String keyword,
                             @RequestParam(name = "filter") String filter,
                             @RequestParam(name = "hotelId") Long hotelId,
                             @RequestParam(name = "room_id") Long roomId,
                             @RequestParam(name = "checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
                             @RequestParam(name = "checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate,
                             @RequestParam(name = "people") Integer people,
                             @RequestParam(name = "room") Integer room){
        bookedRoomService.createBookedRoom(roomId, checkInDate, checkOutDate);
        return "redirect:/hotel/hotel_details?hotelId=" + hotelId
                                            + "&keyword=" + keyword
                                            + "&filter=" + filter
                                            + "&checkin=" + checkInDate
                                            + "&checkout=" + checkOutDate
                                            + "&people=" + people
                                            + "&room=" + room;
    }
}
