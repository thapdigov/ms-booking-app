package az.perfect.msbookingapp.model.dto.request;

import az.perfect.msbookingapp.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateBookingRequest {

    @NotNull
    private Long seatNumber;
    @NotNull
    @Min(1)
    private Long flightId;
    @NotNull
    @Min(1)
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Status bookingStatus;
}
