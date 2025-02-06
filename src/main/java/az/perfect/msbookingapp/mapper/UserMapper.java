package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.dto.response.UserDto;

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
        return null;
    }
}
