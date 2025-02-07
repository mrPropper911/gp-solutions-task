package by.belyahovich.bookingdemo.service;

import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.dto.HotelDto;
import by.belyahovich.bookingdemo.dto.HotelMapper;
import by.belyahovich.bookingdemo.dto.HotelShortInfoDto;
import by.belyahovich.bookingdemo.exception.EntityAlreadyExistsException;
import by.belyahovich.bookingdemo.exception.EntityNotFoundException;
import by.belyahovich.bookingdemo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Autowired
    public HotelService(HotelRepository hotelRepository,
                        HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    public List<HotelShortInfoDto> getAllHotelsWithShortInfo() {
        return hotelRepository.findAll().stream()
                .map(HotelMapper.INSTANCE::toHotelShortInfoDto)
                .collect(Collectors.toList());
    }

    public HotelDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with ID: " + id + " not found"));
        return hotelMapper.toHotelDto(hotel);
    }

    //todo проверить transactional
    @Transactional
    public HotelShortInfoDto saveHotel(Hotel hotel) {
        if (hotelRepository.existsByName(hotel.getName())) {
            throw new EntityAlreadyExistsException("Hotel with name: " + hotel.getName() + " already exists");
        }
        return hotelMapper.toHotelShortInfoDto(hotelRepository.save(hotel));
    }

    public List<Hotel> searchHotel(String name, String brand, String city) {
        Specification<Hotel> specification = Specification
                .where(name == null ? null : HotelSpecifications.hasName(name))
                .and(brand == null ? null : HotelSpecifications.hasBrand(brand))
                .and(city == null ? null : HotelSpecifications.hasCity(city));
        return hotelRepository.findAll(specification);
    }
}
