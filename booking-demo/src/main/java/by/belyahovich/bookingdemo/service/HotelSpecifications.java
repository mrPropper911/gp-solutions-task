package by.belyahovich.bookingdemo.service;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.Hotel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecifications {

    public static Specification<Hotel> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Hotel> hasBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("brand"), brand);
    }

    public static Specification<Hotel> hasCity(String city) {
        return (root, query, criteriaBuilder) -> {
            Join<Hotel, Address> addressJoin = root.join("address");
            return criteriaBuilder.equal(addressJoin.get("city"), city);
        };
    }

    //todo county, amenities
}
