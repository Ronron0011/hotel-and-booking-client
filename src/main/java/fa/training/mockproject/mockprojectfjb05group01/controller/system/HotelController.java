package fa.training.mockproject.mockprojectfjb05group01.controller.system;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.HotelDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.system.RoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.service.system.HotelService;

import fa.training.mockproject.mockprojectfjb05group01.service.system.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;
    private final RoomService roomService;
    public static final String REDIRECT_HOTEL_LOCATION = "redirect:/hotel/list-hotel";

    @Autowired
    public HotelController(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @GetMapping("/search-hotel")
    public String searchHotel(Model model,
                              @RequestParam(name = "keyword") String keyword,
                              @RequestParam(name = "filter") String filter,
                              @RequestParam(name = "checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkinDate,
                              @RequestParam(name = "checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkoutDate,
                              @RequestParam(name = "people") Integer people,
                              @RequestParam(name = "room") Integer room) {

        List<HotelDTO> hotelData = null;

        if (checkinDate != null && checkoutDate != null && people != null && room != null) {
            hotelData = hotelService.searchHotels(keyword, filter, checkinDate, checkoutDate, people, room);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", filter);
        model.addAttribute("checkin", checkinDate);
        model.addAttribute("checkout", checkoutDate);
        model.addAttribute("people", people);
        model.addAttribute("room", room);
        model.addAttribute("listHotel", hotelData);
        return "hotel/listing";
    }

    @GetMapping("/hotel_details")
    public String hotelDetails(Model model,
                               @RequestParam(name = "keyword") String keyword,
                               @RequestParam(name = "filter") String filter,
                               @RequestParam(name = "hotelId") Long hotelId,
                               @RequestParam(name = "checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkinDate,
                               @RequestParam(name = "checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkoutDate,
                               @RequestParam(name = "people") Integer people,
                               @RequestParam(name = "room") Integer room)
    {
        List<RoomDTO> roomDTOList = roomService.listRoomsAvailableByHotelId(hotelId, checkinDate, checkoutDate);
        model.addAttribute("hotel", hotelService.getHotelById(hotelId));
        model.addAttribute("roomDTOList", roomDTOList);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("checkin", checkinDate);
        model.addAttribute("checkout", checkoutDate);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", filter);
        model.addAttribute("people", people);
        model.addAttribute("numberOfRoom", room);
        return "hotel/hotelT";
    }

}
