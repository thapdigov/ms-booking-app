package az.perfect.msbookingapp.domain.repository;

import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.model.enums.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    boolean existsByAirlineNameAndDepartureCityAndArrivalCityAndDepartureAirportAndArrivalAirportAndDepartureTime(
            String airlineName, City departureCity, City arrivalCity, String departureAirport,
            String arrivalAirport, LocalDateTime departureTime);

}
