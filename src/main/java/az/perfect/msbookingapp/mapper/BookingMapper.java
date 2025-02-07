package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.BookingEntity;
import az.perfect.msbookingapp.model.dto.response.BookingDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper implements Mapper<BookingEntity, BookingDto> {
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
        return BookingDto.builder()
                .seatNumber(bookingEntity.getSeatNumber())
                .user(bookingEntity.getUser())
                .flight(bookingEntity.getFlight())
                .bookingStatus(bookingEntity.getBookingStatus())
                .build();
    }
}
