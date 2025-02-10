package by.belyahovich.bookingdemo.service;

import by.belyahovich.bookingdemo.domain.Amenity;
import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.dto.HotelCreateDto;
import by.belyahovich.bookingdemo.dto.HotelDto;
import by.belyahovich.bookingdemo.dto.HotelMapper;
import by.belyahovich.bookingdemo.dto.HotelShortInfoDto;
import by.belyahovich.bookingdemo.exception.EntityAlreadyExistsException;
import by.belyahovich.bookingdemo.exception.EntityNotFoundException;
import by.belyahovich.bookingdemo.repository.amenity.AmenityRepository;
import by.belyahovich.bookingdemo.repository.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final HotelMapper hotelMapper;

    @Autowired
    public HotelService(HotelRepository hotelRepository,
                        AmenityRepository amenityRepository,
                        HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.amenityRepository = amenityRepository;
        this.hotelMapper = hotelMapper;
    }

    public List<HotelShortInfoDto> getAllHotelsWithShortInfo() {
        return hotelRepository.findAll().stream()
                .map(HotelMapper.INSTANCE::toHotelShortInfoDto)
                .collect(Collectors.toList());
    }

    public HotelDto getHotelById(Long id) {
        var hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with ID: " +
                        id + " not found"));
        return hotelMapper.toHotelDto(hotel);
    }

    @Transactional
    public HotelShortInfoDto saveHotel(HotelCreateDto hotelCreateDto) {
        if (hotelRepository.existsByName(hotelCreateDto.getName())) {
            throw new EntityAlreadyExistsException("Hotel with name: " +
                    hotelCreateDto.getName() + " already exists");
        }
        var hotel = hotelMapper.toHotel(hotelCreateDto);
        var savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toHotelShortInfoDto(savedHotel);
    }

    @Transactional
    public void addAmenitiesToHotel(Long id, List<String> amenities) {
        var hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        Set<Amenity> amenitiesToAdd = findOrCreateAmenities(amenities);
        if (hotel.getAmenities() == null) {
            hotel.setAmenities(amenitiesToAdd);
        } else {
            hotel.getAmenities().addAll(amenitiesToAdd);
        }
        hotelRepository.save(hotel);
    }

    private Set<Amenity> findOrCreateAmenities(List<String> amenities) {
        return amenities.stream()
                .map(name -> amenityRepository.findByName(name)
                        .orElseGet(() -> {
                            Amenity newAmenity = Amenity.builder()
                                    .name(name)
                                    .build();
                            return amenityRepository.save(newAmenity);
                        }))
                .collect(Collectors.toSet());
    }

    public List<HotelShortInfoDto> searchHotel(String name, String brand, String city,
                                               String county, String amenities) {
        Specification<Hotel> specification = Specification
                .where(HotelSpecifications.likeName(name))
                .and(HotelSpecifications.likeBrand(brand))
                .and(HotelSpecifications.likeCity(city))
                .and(HotelSpecifications.likeCounty(county))
                .and(HotelSpecifications.likeAmenities(amenities));
        return hotelRepository.findAll(specification).stream()
                .map(hotelMapper::toHotelShortInfoDto)
                .collect(Collectors.toList());
    }
}
