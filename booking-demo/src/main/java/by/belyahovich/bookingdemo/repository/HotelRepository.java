package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {
    List<Hotel> findAll(Specification<Hotel> specification);
    Optional<Hotel> findById(Long id);
}
