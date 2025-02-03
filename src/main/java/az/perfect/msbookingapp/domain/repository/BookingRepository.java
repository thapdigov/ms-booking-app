package az.perfect.msbookingapp.domain.repository;

import az.perfect.msbookingapp.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
