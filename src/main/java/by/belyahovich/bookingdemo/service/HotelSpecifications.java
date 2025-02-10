package by.belyahovich.bookingdemo.service;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.Amenity;
import by.belyahovich.bookingdemo.domain.Hotel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecifications {

    public static Specification<Hotel> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Hotel> likeName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Hotel> likeBrand(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (brand == null || brand.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("brand"), "%" + brand + "%");
        };
    }

    public static Specification<Hotel> likeCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null || city.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Address> addressJoin = root.join("address");
            return criteriaBuilder.like(addressJoin.get("city"), "%" + city + "%");
        };
    }

    public static Specification<Hotel> likeCounty(String county) {
        return (root, query, criteriaBuilder) -> {
            if (county == null || county.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Address> addressJoin = root.join("address");
            return criteriaBuilder.like(addressJoin.get("county"), "%" + county + "%");
        };
    }

    public static Specification<Hotel> likeAmenities(String amenitiesName) {
        return (root, query, criteriaBuilder) -> {
            if (amenitiesName == null || amenitiesName.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Amenity> amenityJoin = root.join("amenities", JoinType.INNER);
            return criteriaBuilder.like(amenityJoin.get("name"), "%" + amenitiesName + "%");
        };
    }
}
