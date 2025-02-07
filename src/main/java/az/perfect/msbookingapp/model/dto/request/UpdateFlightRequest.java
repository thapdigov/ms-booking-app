package az.perfect.msbookingapp.model.dto.request;

import az.perfect.msbookingapp.model.enums.AirCraftModel;
import az.perfect.msbookingapp.model.enums.City;
import az.perfect.msbookingapp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFlightRequest {

    @NotBlank
    private String airlineName;
    @NotNull
    private City departureCity;
    @NotNull
    private City arrivalCity;
    @NotBlank
    private String departureAirport;
    @NotBlank
    private String arrivalAirport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime departureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime arrivalTime;
    @NotNull
    private AirCraftModel airCraftModel;
    @NotBlank
    private String departureTerminal;
    @NotBlank
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
