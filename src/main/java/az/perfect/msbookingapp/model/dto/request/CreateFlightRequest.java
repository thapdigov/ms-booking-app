package az.perfect.msbookingapp.model.dto.request;

import az.perfect.msbookingapp.model.enums.AirCraftModel;
import az.perfect.msbookingapp.model.enums.City;
import az.perfect.msbookingapp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String airlineName;
    @NotNull
    private City departureCity;
    @NotNull
    private City arrivalCity;
    @NotNull
    private String departureAirport;
    @NotNull
    private String arrivalAirport;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime departureTime;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime arrivalTime;
    @NotNull
    private AirCraftModel airCraftModel;
    @NotNull
    private String departureTerminal;
    @NotNull
    private String arrivalTerminal;
    @NotNull
    private Integer gateNumber;
    @NotNull
    private Integer maxBaggageWeight;
    @NotNull
    private Boolean isWifiAvailable;
    @NotNull
    private Integer availableSeats;
    @NotNull
    private Integer maxSeats;
    @NotNull
    private Status flightStatus;
}
