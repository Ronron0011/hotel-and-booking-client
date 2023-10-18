package fa.training.mockproject.mockprojectfjb05group01.controller.exception.hotelserviceexception;

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(String message) {
        super(message);
    }
}