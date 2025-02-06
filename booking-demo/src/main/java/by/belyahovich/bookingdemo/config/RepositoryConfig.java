package by.belyahovich.bookingdemo.config;

import by.belyahovich.bookingdemo.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RepositoryConfig {

    @Bean
    @Profile("h2-test")
    public HotelRepository hotelRepositoryH2(HotelJpaRepository hotelJpaRepository){
        return new HotelRepositoryH2Impl(hotelJpaRepository);
    }

    @Bean
    @Profile({"mysql-test", "mysql-prod"})
    public HotelRepository hotelRepositoryMySql(HotelJpaRepository hotelJpaRepository) {
        return new HotelRepositoryMySqlImpl(hotelJpaRepository);
    }

    @Bean
    @Profile("postgresql-test")
    public HotelRepository hotelRepositoryPostgreSql(HotelJpaRepository hotelJpaRepository) {
        return new HotelRepositoryPostgreSqlImpl(hotelJpaRepository);
    }


}
