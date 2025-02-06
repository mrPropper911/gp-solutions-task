package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelJpaRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findAllByAddress(Address address);
}
