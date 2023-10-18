package fa.training.mockproject.mockprojectfjb05group01.controller.client;

import fa.training.mockproject.mockprojectfjb05group01.dto.client.ClientDTO;
import fa.training.mockproject.mockprojectfjb05group01.service.client.ClientService;
import fa.training.mockproject.mockprojectfjb05group01.service.system.HotelService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class ClientController {
    private final ClientService clientService;
    private final HotelService hotelService;

    public ClientController(ClientService clientService, HotelService hotelService) {
        this.clientService = clientService;
        this.hotelService = hotelService;
    }

    @GetMapping("/main")
    public String showIndex() {
        return "hotel/index";
    }

//    @PostMapping("/search")
//    public String findHotels(Model model, @RequestParam(required = false, name = "field") String field
//            ,@RequestParam(required = false, name = "name") String name ) {
//        List<HotelDTO> hotels = new ArrayList<>();
//
//        if ("hotelName".equals(field)) {
//            hotels = hotelService.searchByHotelsName(name);
//        } else if ("address".equals(field)) {
//            hotels = hotelService.searchByAddress(name);
//        } else if ("city".equals(field)) {
//            hotels = hotelService.searchByCity(name);
//        } else if ("country".equals(field)) {
//            hotels = hotelService.searchByCountry(name);
//        }
//        if(hotels.isEmpty()) {
//
//            model.addAttribute("error","lỗi lắm nek");
//        }
//
//        model.addAttribute("hotels", hotels);
//        return "/hotel/listing";
//    }

    //list sau khi search
    @GetMapping("/list")
    public String showList() {

        return "/hotel/listing";
    }

    // update
    @GetMapping("/show-update/{email}")
    public String showUpdateForm(@PathVariable String email, Model model) {
        model.addAttribute("client", clientService.findClientByEmail(email));
        return "/client/update";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute("client") ClientDTO clientDto) {
        clientService.updateClient(clientDto);
        return "redirect:/main";
    }
}
