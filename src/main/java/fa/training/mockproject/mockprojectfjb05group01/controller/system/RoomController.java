package fa.training.mockproject.mockprojectfjb05group01.controller.system;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.RoomDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.Hotel;
import fa.training.mockproject.mockprojectfjb05group01.service.system.HotelService;
import fa.training.mockproject.mockprojectfjb05group01.service.system.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public final static String REDIRECT_ROOM_LOCATION = "redirect:/room/list-room";

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/list-room")
    private String listRoom(Model model) {
        model.addAttribute("listRoom", roomService.getAllRoom());
        return "room/list-room";
    }
}
