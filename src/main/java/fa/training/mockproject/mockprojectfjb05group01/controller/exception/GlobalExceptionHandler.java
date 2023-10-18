package fa.training.mockproject.mockprojectfjb05group01.controller.exception;

import fa.training.mockproject.mockprojectfjb05group01.controller.exception.hotelserviceexception.HotelNotFoundException;
import fa.training.mockproject.mockprojectfjb05group01.controller.exception.hotelserviceexception.InternalServerErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    //bắt các lỗi chung
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        e.printStackTrace();  // Thực tế người ta dùng logger
        return ResponseEntity.status(500).body("Unknown error");
    }

    //bắt lỗi bên HotelService - cụ thể là list trả về rỗng
    @ExceptionHandler(InternalServerErrorException.class)
    public ModelAndView handleInternalServerErrorException(InternalServerErrorException e) {
        e.printStackTrace();  // Thực tế người ta dùng logger
        ModelAndView modelAndView = new ModelAndView("error"); // Chỉ định trang lỗi tại đây
        modelAndView.addObject("errorMessage", "An internal server error occurred");
        return modelAndView;
    }

    // bắt lỗi bên HotelService - cụ thể là khong tìm thấy hotels
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<String> handleHotelNotFoundException(HotelNotFoundException e) {
        e.printStackTrace();  // Thực tế người ta dùng logger
        return ResponseEntity.status(404).body("No hotels found");
    }

    @ExceptionHandler(AccountDisabledException.class)
    public ModelAndView handleAccountDisabledException(AccountDisabledException e){
        e.printStackTrace();
        ModelAndView modelAndView  = new ModelAndView("error");
        modelAndView.addObject("errorMessage","Account has been disabled, please contact the admin of the application for support");
        return modelAndView;
    }

}
