package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.BookingEntity;
import az.perfect.msbookingapp.domain.entity.FlightDetailEntity;
import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.dto.response.BookingDto;
import az.perfect.msbookingapp.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper implements Mapper<BookingEntity, BookingDto> {

    private final UserMapper userMapper;

    @Override
    public BookingEntity toEnt(BookingDto bookingDto) {
        return BookingEntity.builder()
                .seatNumber(bookingDto.getSeatNumber())
                .user(bookingDto.getUser())
                .flight(bookingDto.getFlight())
                .bookingStatus(bookingDto.getBookingStatus())
                .build();
    }

    @Override
    public BookingDto toDto(BookingEntity bookingEntity) {
        UserEntity existingUser = bookingEntity.getUser();
        UserDto user = UserDto.builder()
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName())
                .phoneNumber(existingUser.getPhoneNumber())
                .email(existingUser.getEmail())
                .nationality(existingUser.getNationality())
                .dateOfBirth(existingUser.getDateOfBirth())
                .role(existingUser.getRole())
                .build();

        FlightEntity existsFlight = bookingEntity.getFlight();
        FlightEntity flight = new FlightEntity();
        flight.setId(existsFlight.getId());
        flight.setAirlineName(existsFlight.getAirlineName());
        flight.setDepartureCity(existsFlight.getDepartureCity());
        flight.setArrivalCity(existsFlight.getArrivalCity());
        existsFlight.setArrivalTime(existsFlight.getArrivalTime());
        flight.setDepartureAirport(existsFlight.getDepartureAirport());
        flight.setArrivalAirport(existsFlight.getArrivalAirport());
        flight.setDepartureTime(existsFlight.getDepartureTime());
        flight.setArrivalTime(existsFlight.getArrivalTime());
        FlightDetailEntity detail = new FlightDetailEntity();
        detail.setId(existsFlight.getFlightDetail().getId());
        detail.setAirCraftModel(existsFlight.getFlightDetail().getAirCraftModel());
        detail.setDepartureTerminal(existsFlight.getFlightDetail().getDepartureTerminal());
        detail.setArrivalTerminal(existsFlight.getFlightDetail().getArrivalTerminal());
        detail.setGateNumber(existsFlight.getFlightDetail().getGateNumber());
        detail.setMaxBaggageWeight(existsFlight.getFlightDetail().getMaxBaggageWeight());
        detail.setArrivalTerminal(existsFlight.getFlightDetail().getArrivalTerminal());
        flight.setFlightDetail(detail);
        return BookingDto.builder()
                .seatNumber(bookingEntity.getSeatNumber())
                .user(userMapper.toEnt(user))
                .flight(flight)
                .bookingStatus(bookingEntity.getBookingStatus())
                .build();
    }
}
