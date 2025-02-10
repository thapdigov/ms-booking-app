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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto create(CreateUserRequest request) {
        if (userEntityRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExists("User already exists with " + request.getEmail());
        }
        UserEntity savedEntity = userEntityRepository.save(userMapper.toEnt(request));
        return userMapper.toDto(savedEntity);
    }

    public Page<UserDto> findAllUsers(Pageable pageable) {
        Page<UserEntity> entityPage = userEntityRepository.findAll(pageable);
        return entityPage.map(userMapper::toDto);
    }

    @Transactional
    public UserDto updateUser(Long id, UpdateUserRequest request) {
        UserEntity user = findById(id);
        user = userMapper.toEnt(user, request);
        user.setUpdatedBy(id);
        UserEntity updatedEntity = userEntityRepository.save(user);
        return userMapper.toDto(updatedEntity);
    }

    @Transactional
    public void deleteUserById(Long id) {
        UserEntity deletedUser = findById(id);
        deletedUser.setStatus(Status.DELETE);
        userEntityRepository.save(deletedUser);
    }

    @Transactional
    public UserDto findUserById(Long id) {
        UserEntity user = findById(id);
        return userMapper.toDto(user);
    }

    private UserEntity findById(Long id) {
        return userEntityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with " + id));
    }


}
