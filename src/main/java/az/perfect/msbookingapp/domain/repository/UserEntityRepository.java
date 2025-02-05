package az.perfect.msbookingapp.domain.repository;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    boolean findByIdAndRole(Long id, Role role);
}
