package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.BookingEntity;
import az.perfect.msbookingapp.domain.entity.FlightDetailEntity;
import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.dto.request.CreateBookingRequest;
import az.perfect.msbookingapp.model.dto.response.BookingDto;
import az.perfect.msbookingapp.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "user", ignore = true)
    BookingEntity toEnt(BookingDto bookingDto);

    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "user", ignore = true)
    BookingEntity toEnt(CreateBookingRequest request);

    @Mapping(target = "flightId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    BookingDto toDto(BookingEntity bookingEntity);
}
