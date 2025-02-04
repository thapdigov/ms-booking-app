package az.perfect.msbookingapp.model.request;

import az.perfect.msbookingapp.model.enums.AirCraftModel;
import az.perfect.msbookingapp.model.enums.City;
import az.perfect.msbookingapp.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CreateFlightRequest {

    @NotBlank
    private String airlineName;
    @NotBlank
    private City departureCity;
    @NotBlank
    private City arrivalCity;
    @NotBlank
    private String departureAirport;
    @NotBlank
    private String arrivalAirport;
    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime departureTime;
    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime arrivalTime;
    @NotBlank
    private AirCraftModel airCraftModel;
    @NotBlank
    private String departureTerminal;
    @NotBlank
    private String arrivalTerminal;
    @NotBlank
    private Integer gateNumber;
    @NotBlank
    private Integer maxBaggageWeight;
    @NotBlank
    private Boolean isWifiAvailable;
    @NotBlank
    private Integer availableSeats;
    @NotBlank
    private Integer maxSeats;
    @NotBlank
    private Status flightStatus;
}
