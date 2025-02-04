package az.perfect.msbookingapp.domain.entity;

import az.perfect.msbookingapp.model.enums.City;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
@SuperBuilder
public class FlightEntity extends BaseEntity {

    @Column(name = "airline_name", nullable = false, unique = true, updatable = false)
    private String airlineName;

    @Enumerated(EnumType.STRING)
    @Column(name = "departure_city", nullable = false)
    private City departureCity;

    @Enumerated(EnumType.STRING)
    @Column(name = "arrival_city", nullable = false)
    private City arrivalCity;

    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy/ HH:mm:ss")
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FlightDetailEntity flightDetail;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingEntity> flights;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TicketEntity> tickets;
}
