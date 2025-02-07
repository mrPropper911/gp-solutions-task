package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class HotelRepositoryMySqlImpl implements HotelRepository {
    private final HotelJpaRepository hotelJpaRepository;

    public HotelRepositoryMySqlImpl(HotelJpaRepository hotelJpaRepository) {
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
        Optional<Hotel> byId = hotelJpaRepository.findById(id);
        Optional<Hotel> awd = hotelJpaRepository.findAllByAddress(new Address());
        return Optional.empty();
    }
}
