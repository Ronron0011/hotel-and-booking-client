package fa.training.mockproject.mockprojectfjb05group01.controller.booking;

import fa.training.mockproject.mockprojectfjb05group01.dto.booking.BookedRoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.booking.BookedRoom;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.BookedRoomStatus;
import fa.training.mockproject.mockprojectfjb05group01.service.booking.BookedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookedRoomService bookedRoomService;

    @GetMapping("/booked-rooms")
    public String showBookedRoomList(Model model) {
//        List<BookedRoomDTO> dataList = bookedRoomService.getAllBookedRoomByStatus(BookedRoomStatus.WAIT);
        List<BookedRoom> bookedRooms = bookedRoomService.getAllBookedRoomByStatus(BookedRoomStatus.WAIT);
        model.addAttribute("bookedRooms", bookedRooms);
        return "client/booking";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        bookedRoomService.deleteBookedRoomById(id);
        return "redirect:/booking/booked-rooms";
    }
}
