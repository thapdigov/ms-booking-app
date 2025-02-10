package az.perfect.msbookingapp.model.dto.response;

import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDto {
    private Long seatNumber;
    private Long  flightId;
    private Long userId;
    private Status bookingStatus;
}
