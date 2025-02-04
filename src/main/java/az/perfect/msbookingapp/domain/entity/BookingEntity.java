package az.perfect.msbookingapp.domain.entity;

import az.perfect.msbookingapp.model.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "booking")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity extends BaseEntity {

    @Column(name = "seat_number", nullable = false, unique = true)
    private Long seatNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private Status bookingStatus;
}
