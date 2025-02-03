package az.perfect.msbookingapp.domain.repository;

import az.perfect.msbookingapp.domain.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
}
