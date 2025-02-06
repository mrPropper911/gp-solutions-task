package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class HotelRepositoryMySqlImpl implements HotelRepository{
    private final HotelJpaRepository hotelJpaRepository;

    public HotelRepositoryMySqlImpl(HotelJpaRepository hotelJpaRepository) {
        this.hotelJpaRepository = hotelJpaRepository;
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        Optional<Hotel> byId = hotelJpaRepository.findById(id);
        Optional<Hotel> awd = hotelJpaRepository.findAllByAddress(new Address());
        return Optional.empty();
    }
}
