package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Hotel;

import java.util.Optional;

public class HotelRepositoryH2Impl implements HotelRepository {
    private final HotelJpaRepository hotelJpaRepository;

    public HotelRepositoryH2Impl(HotelJpaRepository hotelJpaRepository) {
        this.hotelJpaRepository = hotelJpaRepository;
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelJpaRepository.findById(id);
    }
}
