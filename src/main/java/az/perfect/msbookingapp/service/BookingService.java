package az.perfect.msbookingapp.service;

import az.perfect.msbookingapp.domain.entity.BookingEntity;
import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.domain.repository.BookingRepository;
import az.perfect.msbookingapp.domain.repository.FlightRepository;
import az.perfect.msbookingapp.domain.repository.UserEntityRepository;
import az.perfect.msbookingapp.exception.NotFoundException;
import az.perfect.msbookingapp.mapper.BookingMapper;
import az.perfect.msbookingapp.model.dto.request.CreateBookingRequest;
import az.perfect.msbookingapp.model.dto.response.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserEntityRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper mapper;

    public Page<BookingDto> findAllBooking(Pageable pageable) {
        return bookingRepository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional
    public BookingDto createBooking(CreateBookingRequest request) {

        FlightEntity flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new NotFoundException("Flight not found with " + request.getFlightId()));

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with " + request.getUserId()));

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setSeatNumber(request.getSeatNumber());
        bookingEntity.setFlight(flight);
        bookingEntity.setUser(user);
        bookingEntity.setBookingStatus(request.getBookingStatus());
        bookingEntity.setCreatedBy(request.getUserId());
        bookingEntity.setUpdatedBy(request.getUserId());
        BookingEntity savedBooking = bookingRepository.save(bookingEntity);
        return mapper.toDto(savedBooking);
    }
}
