package by.belyahovich.bookingdemo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private String brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id", referencedColumnName = "id")
    private Contacts contacts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_time_id", referencedColumnName = "id")
    private ArrivalTime arrivalTime;

    @ElementCollection
    @CollectionTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenities")
    private Set<String> amenities;
}
