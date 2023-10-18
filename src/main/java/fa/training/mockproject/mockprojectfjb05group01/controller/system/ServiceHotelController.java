package fa.training.mockproject.mockprojectfjb05group01.controller.system;

import fa.training.mockproject.mockprojectfjb05group01.dto.system.ServiceHotelDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.system.ServiceHotel;
import fa.training.mockproject.mockprojectfjb05group01.service.system.ServiceHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceHotelController {
    private final ServiceHotelService serviceHotelService;

    @Autowired
    public ServiceHotelController(ServiceHotelService serviceHotelService) {
        this.serviceHotelService = serviceHotelService;
    }

    @GetMapping("/listService")
    public ModelAndView listServices() {
        List<ServiceHotelDTO> serviceList = serviceHotelService.getAllServiceHotel();
        ModelAndView modelAndView = new ModelAndView("hotel/list-service");
        modelAndView.addObject("serviceList", serviceList);
        return modelAndView;
    }
}