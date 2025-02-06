package az.perfect.msbookingapp.domain.repository;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
