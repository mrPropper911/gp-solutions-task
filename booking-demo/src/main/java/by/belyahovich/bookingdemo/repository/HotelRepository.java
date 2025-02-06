package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Hotel;

import java.util.Optional;

public interface HotelRepository {
    Optional<Hotel> findById(Long id);
}
