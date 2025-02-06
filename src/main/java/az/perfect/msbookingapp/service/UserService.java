package az.perfect.msbookingapp.service;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.domain.repository.UserEntityRepository;
import az.perfect.msbookingapp.exception.AlreadyExists;
import az.perfect.msbookingapp.mapper.UserMapper;
import az.perfect.msbookingapp.model.dto.response.UserDto;
import az.perfect.msbookingapp.model.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository entityRepository;
    private final UserMapper userMapper;

    public UserDto create(CreateUserRequest request) {
        if (entityRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExists("User already exists with " + request.getEmail());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setRole(request.getRole());
        userEntity.setPhoneNumber(request.getPhoneNumber());
        userEntity.setDateOfBirth(request.getBirthDay());
        userEntity.setNationality(request.getNationality());
        UserEntity savedEntity = entityRepository.save(userEntity);
        return userMapper.toDto(savedEntity);
    }
}
