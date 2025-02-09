package by.belyahovich.bookingdemo.config;

import by.belyahovich.bookingdemo.repository.amenity.AmenityJpaRepository;
import by.belyahovich.bookingdemo.repository.amenity.AmenityRepository;
import by.belyahovich.bookingdemo.repository.amenity.AmenityRepositoryH2Impl;
import by.belyahovich.bookingdemo.repository.amenity.AmenityRepositoryMySqlImpl;
import by.belyahovich.bookingdemo.repository.hotel.HotelJpaRepository;
import by.belyahovich.bookingdemo.repository.hotel.HotelRepository;
import by.belyahovich.bookingdemo.repository.hotel.HotelRepositoryH2Impl;
import by.belyahovich.bookingdemo.repository.hotel.HotelRepositoryMySqlImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RepositoryConfig {

    @Bean
    @Profile("h2-test")
    public HotelRepository hotelRepositoryH2(HotelJpaRepository hotelJpaRepository) {
        return new HotelRepositoryH2Impl(hotelJpaRepository);
    }

    @Bean
    @Profile({"mysql-test", "mysql-prod"})
    public HotelRepository hotelRepositoryMySql(HotelJpaRepository hotelJpaRepository) {
        return new HotelRepositoryMySqlImpl(hotelJpaRepository);
    }

    @Bean
    @Profile("h2-test")
    public AmenityRepository amenityRepositoryH2(AmenityJpaRepository amenityJpaRepository) {
        return new AmenityRepositoryH2Impl(amenityJpaRepository);
    }

    @Bean
    @Profile({"mysql-test", "mysql-prod"})
    public AmenityRepository amenityRepositoryMySql(AmenityJpaRepository amenityJpaRepository) {
        return new AmenityRepositoryMySqlImpl(amenityJpaRepository);
    }
}
