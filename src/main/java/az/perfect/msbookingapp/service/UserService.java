package az.perfect.msbookingapp.service;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.domain.repository.UserEntityRepository;
import az.perfect.msbookingapp.exception.AlreadyExists;
import az.perfect.msbookingapp.exception.NotFoundException;
import az.perfect.msbookingapp.mapper.UserMapper;
import az.perfect.msbookingapp.model.dto.request.CreateUserRequest;
import az.perfect.msbookingapp.model.dto.request.UpdateUserRequest;
import az.perfect.msbookingapp.model.dto.response.UserDto;
import az.perfect.msbookingapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    public UserDto create(CreateUserRequest request) {
        if (userEntityRepository.existsByEmail(request.getEmail())) {
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
        userEntity.setStatus(request.getStatus());
        UserEntity savedEntity = userEntityRepository.save(userEntity);
        return userMapper.toDto(savedEntity);
    }

    public Page<UserDto> findAllUsers(Pageable pageable) {
        Page<UserEntity> entityPage = userEntityRepository.findAll(pageable);
        return entityPage.map(userMapper::toDto);
    }

    public UserDto updateUser(Long id, UpdateUserRequest request) {
        UserEntity user = findById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setNationality(request.getNationality());
        user.setCreatedBy(id);
        UserEntity updatedEntity = userEntityRepository.save(user);
        return userMapper.toDto(updatedEntity);
    }

    public void deleteUserById(Long id) {
        UserEntity deletedUser = findById(id);
        deletedUser.setStatus(Status.DELETE);
        userEntityRepository.save(deletedUser);
    }

    public UserDto findUserById(Long id) {
        UserEntity user = findById(id);
        return userMapper.toDto(user);
    }

    private UserEntity findById(Long id) {
        return userEntityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with " + id));
    }


}
