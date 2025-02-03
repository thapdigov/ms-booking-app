package az.perfect.msbookingapp.domain.entity;

import az.perfect.msbookingapp.model.enums.AirCraftModel;
import az.perfect.msbookingapp.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "flightdetail")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDetailEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "aircraft_model", nullable = false, unique = true)
    private AirCraftModel airCraftModel;

    @Column(name = "departure_terminal", nullable = false, unique = true)
    private String departureTerminal;

    @Column(name = "arrival_terminal", nullable = false, unique = true)
    private String arrivalTerminal;

    @Column(name = "gate_number", nullable = false)
    private Integer gateNumber;

    @Column(name = "baggage_weight", nullable = false)
    private Integer maxBaggageWeight;

    @Column(name = "is_wifi_available", nullable = false)
    private Boolean isWifiAvailable;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "max_seats", nullable = false)
    private Integer maxSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private Status flightStatus;
}
