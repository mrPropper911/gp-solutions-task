package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class HotelRepositoryPostgreSqlImpl implements HotelRepository{
    private final HotelJpaRepository hotelJpaRepository;

    public HotelRepositoryPostgreSqlImpl(HotelJpaRepository hotelJpaRepository) {
        this.hotelJpaRepository = hotelJpaRepository;
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelJpaRepository.findById(id);
    }
}
