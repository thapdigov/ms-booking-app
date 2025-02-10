package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.model.dto.request.CreateUserRequest;
import az.perfect.msbookingapp.model.dto.request.UpdateUserRequest;
import az.perfect.msbookingapp.model.dto.response.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "createdAt", expression = ("java(java.time.LocalDateTime.now())"))
    @Mapping(target = "updatedAt", expression = ("java(java.time.LocalDateTime.now())"))
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    UserEntity toEnt(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "dateOfBirth", source = "birthDay")
    @Mapping(target = "createdAt", expression = ("java(java.time.LocalDateTime.now())"))
    @Mapping(target = "updatedAt", expression = ("java(java.time.LocalDateTime.now())"))
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    UserEntity toEnt(CreateUserRequest request);


    UserEntity toEnt(@MappingTarget UserEntity entity, UpdateUserRequest request);
}
