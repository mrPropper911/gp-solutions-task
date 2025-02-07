package by.belyahovich.bookingdemo.controller;

import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.dto.HotelDto;
import by.belyahovich.bookingdemo.dto.HotelShortInfoDto;
import by.belyahovich.bookingdemo.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    //todo response
    @GetMapping("/hotels")
    public List<HotelShortInfoDto> getAllHotels() {
       return hotelService.getAllHotelsWithShortInfo();
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id){
        HotelDto hotelDto = hotelService.getHotelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelDto);
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city) {
        return hotelService.searchHotel(name, brand, city);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelShortInfoDto> saveHotel(@RequestBody Hotel hotel){
        HotelShortInfoDto hotelShortInfoDto = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelShortInfoDto);
    }

}
