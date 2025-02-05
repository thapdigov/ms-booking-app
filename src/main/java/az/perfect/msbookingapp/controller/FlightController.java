package az.perfect.msbookingapp.controller;

import az.perfect.msbookingapp.model.dto.FlightDto;
import az.perfect.msbookingapp.model.dto.request.CreateFlightRequest;
import az.perfect.msbookingapp.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("flight/v1")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/admin/{id}")
    public ResponseEntity<FlightDto> create(@Valid @RequestBody CreateFlightRequest request,
                                            @Min(1) @NotNull @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.create(request, id));
    }
}
