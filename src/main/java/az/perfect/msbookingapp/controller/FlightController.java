package az.perfect.msbookingapp.controller;

import az.perfect.msbookingapp.model.dto.response.FlightDto;
import az.perfect.msbookingapp.model.dto.request.CreateFlightRequest;
import az.perfect.msbookingapp.model.dto.request.UpdateFlightRequest;
import az.perfect.msbookingapp.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<Page<FlightDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "departureTime") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<FlightDto> flights = flightService.getAllFlight(pageable);
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<FlightDto> create(@Valid @RequestBody CreateFlightRequest request,
                                            @Min(1) @NotNull @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.create(request, id));
    }

    @PutMapping("/admin/flihtId/{adminId}/{flightId}")
    public ResponseEntity<FlightDto> update(@Min(1) @NotNull @PathVariable Long adminId,
                                            @Min(1) @NotNull @PathVariable Long flightId,
                                            @Valid @RequestBody UpdateFlightRequest updateFlightRequest
    ) {
        return ResponseEntity.ok(flightService.updateFlight(adminId, flightId, updateFlightRequest));
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDto> getById(@Min(1) @NotNull @PathVariable Long flightId){
        return ResponseEntity.ok(flightService.getById(flightId));
    }


    @DeleteMapping("/admin/{adminId}/{flightId}")
    public ResponseEntity<Void> delete(@Min(1) @NotNull @PathVariable Long adminId,
                                       @Min(1) @NotNull @PathVariable Long flightId) {
        flightService.deleteFlightById(adminId, flightId);
        return ResponseEntity.noContent().build();
    }
}
