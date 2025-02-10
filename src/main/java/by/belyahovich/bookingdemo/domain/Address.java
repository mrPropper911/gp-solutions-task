package by.belyahovich.bookingdemo.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number")
    private int houseNumber;

    private String street;
    private String city;
    private String county;

    @Column(name = "post_code")
    private String postCode;

    @OneToOne(mappedBy = "address")
    private Hotel hotel;

    public String getFullAddress() {
        return houseNumber + " " + street + ", " +
                city + ", " + postCode + ", " + county;
    }
}
