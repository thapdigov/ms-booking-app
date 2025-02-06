package az.perfect.msbookingapp.model.dto.response;

import az.perfect.msbookingapp.domain.entity.FlightDetailEntity;
import az.perfect.msbookingapp.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class FlightDto {

    private City departureCity;

    private City arrivalCity;

    private String departureAirport;

    private String arrivalAirport;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Integer gateNumber;

    private Integer maxBaggageWeight;

    private Boolean isWifiAvailable;

    private Integer availableSeats;

    private Integer maxSeats;
}
