package by.belyahovich.bookingdemo.repository;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HotelJpaRepository extends JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel> {
    Optional<Hotel> findByName(String name);

    Optional<Hotel> findAllByAddress(Address address);
}
