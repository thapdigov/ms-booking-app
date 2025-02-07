package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.dto.response.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {

    @Override
    public UserEntity toEnt(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .phoneNumber(userDto.getPhoneNumber())
                .dateOfBirth(userDto.getDateOfBirth())
                .nationality(userDto.getNationality())
                .build();
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .phoneNumber(userEntity.getPhoneNumber())
                .dateOfBirth(userEntity.getDateOfBirth())
                .nationality(userEntity.getNationality())
                .build();
    }
}
