package az.perfect.msbookingapp.service;

import az.perfect.msbookingapp.domain.entity.BookingEntity;
import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.domain.repository.BookingRepository;
import az.perfect.msbookingapp.domain.repository.FlightRepository;
import az.perfect.msbookingapp.domain.repository.UserEntityRepository;
import az.perfect.msbookingapp.exception.NotFoundException;
import az.perfect.msbookingapp.exception.UnauthorizedAccessException;
import az.perfect.msbookingapp.mapper.BookingMapper;
import az.perfect.msbookingapp.model.dto.request.CreateBookingRequest;
import az.perfect.msbookingapp.model.dto.request.UpdateBookingRequest;
import az.perfect.msbookingapp.model.dto.response.BookingDto;
import az.perfect.msbookingapp.model.enums.Role;
import az.perfect.msbookingapp.model.enums.Status;
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
        return bookingRepository.findAll(pageable).map(bookingEntity -> {
            BookingDto bookingDto = mapper.toDto(bookingEntity);
            bookingDto.setUserId(bookingEntity.getUser().getId());
            bookingDto.setFlightId(bookingEntity.getFlight().getId());
            return bookingDto;
        });
    }

    @Transactional
    public BookingDto createBooking(CreateBookingRequest request) {

        FlightEntity flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new NotFoundException("Flight not found with " + request.getFlightId()));

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with " + request.getUserId()));
        BookingEntity bookingEntity = mapper.toEnt(request);
        bookingEntity.setFlight(flight);
        bookingEntity.setUser(user);
        bookingEntity.setCreatedBy(request.getUserId());
        bookingEntity.setUpdatedBy(request.getUserId());
        BookingEntity savedBooking = bookingRepository.save(bookingEntity);
        BookingDto bookingDto = mapper.toDto(savedBooking);
        bookingDto.setFlightId(savedBooking.getFlight().getId());
        bookingDto.setUserId(savedBooking.getUser().getId());
        return bookingDto;
    }

    public BookingDto updateBooking(Long adminId, Long bookingId, UpdateBookingRequest request) {
        UserEntity user = userRepository.findById(adminId)
                .orElseThrow(() -> new NotFoundException("User not found with " + adminId));
        if (user.getRole().equals(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Only Admin can be updated!");
        }
        BookingEntity bookingEntity = findByIdForMethods(bookingId);
        FlightEntity flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new NotFoundException("Flight not found with " + request.getFlightId()));
        bookingEntity.setSeatNumber(request.getSeatNumber());
        bookingEntity.setFlight(flight);
        bookingEntity.setUser(user);
        bookingEntity.setBookingStatus(Status.ACTIVE);
        BookingEntity updatedBooking = bookingRepository.save(bookingEntity);
        return mapper.toDto(updatedBooking);
    }

    public BookingDto findById(Long bookingId) {
        BookingEntity bookingEntity = findByIdForMethods(bookingId);
        BookingDto bookingDto = mapper.toDto(bookingEntity);
        bookingDto.setFlightId(bookingEntity.getFlight().getId());
        bookingDto.setUserId(bookingEntity.getUser().getId());
        return bookingDto;
    }

    public BookingDto deleteBooking(Long bookingId) {
        BookingEntity deletedBooking = findByIdForMethods(bookingId);
        deletedBooking.setBookingStatus(Status.DELETE);
        BookingEntity save = bookingRepository.save(deletedBooking);
        BookingDto bookingDto = mapper.toDto(save);
        bookingDto.setFlightId(save.getFlight().getId());
        bookingDto.setUserId(save.getUser().getId());
        return bookingDto;
    }

    public BookingEntity findByIdForMethods(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found with " + id));
    }
}
