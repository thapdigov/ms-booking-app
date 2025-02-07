package az.perfect.msbookingapp.controller;

import az.perfect.msbookingapp.model.dto.request.CreateBookingRequest;
import az.perfect.msbookingapp.model.dto.response.BookingDto;
import az.perfect.msbookingapp.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/bookings")
@Validated
public class BookingController {

    public final BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<Page<BookingDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "flightId") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return ResponseEntity.ok(bookingService.findAllBooking(pageable));
    }

    @PostMapping
    public ResponseEntity<BookingDto> create(
            @Valid @RequestBody CreateBookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }
}
