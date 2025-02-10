package by.belyahovich.bookingdemo.repository.hotel;

import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.dto.HistogramDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class HotelRepositoryH2Impl implements HotelRepository {
    private final HotelJpaRepository hotelJpaRepository;

    public HotelRepositoryH2Impl(HotelJpaRepository hotelJpaRepository) {
        this.hotelJpaRepository = hotelJpaRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelJpaRepository.save(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelJpaRepository.findAll();
    }

    @Override
    public List<Hotel> findAll(Specification<Hotel> specification) {
        return hotelJpaRepository.findAll(specification);
    }

    @Override
    public boolean existsByName(String name) {
        return hotelJpaRepository.findByName(name).isPresent();
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelJpaRepository.findById(id);
    }

    @Override
    public List<HistogramDto> getBrandHistogram() {
        return hotelJpaRepository.getBrandHistogram();
    }

    @Override
    public List<HistogramDto> getCityHistogram() {
        return hotelJpaRepository.getCityHistogram();
    }

    @Override
    public List<HistogramDto> getCountyHistogram() {
        return hotelJpaRepository.getCountyHistogram();
    }

    @Override
    public List<HistogramDto> getAmenitiesHistogram() {
        return hotelJpaRepository.getAmenitiesHistogram();
    }
}
