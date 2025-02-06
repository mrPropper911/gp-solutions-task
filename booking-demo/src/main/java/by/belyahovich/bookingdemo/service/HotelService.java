package by.belyahovich.bookingdemo.service;

import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> searchHotel(String name, String brand, String city){
        Specification<Hotel> specification = Specification
                .where(name == null ? null : HotelSpecifications.hasName(name))
                .and(brand == null ? null : HotelSpecifications.hasBrand(brand))
                .and(city == null ? null : HotelSpecifications.hasCity(city));
        return hotelRepository.findAll(specification);
    }
}
