package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.FlightDetailEntity;
import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.model.dto.request.CreateFlightRequest;
import az.perfect.msbookingapp.model.dto.request.UpdateFlightRequest;
import az.perfect.msbookingapp.model.dto.response.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(target = "flightDetail", source = "flightDto", qualifiedByName = "flightDetailMap")
    @Mapping(target = "createdAt", expression = ("java(java.time.LocalDateTime.now())"))
    @Mapping(target = "updatedAt", expression = ("java(java.time.LocalDateTime.now())"))
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    FlightEntity toEnt(FlightDto flightDto);

    @Named("flightDetailMap")
    @Mapping(target = "gateNumber", source = "flightDto.gateNumber")
    @Mapping(target = "maxBaggageWeight", source = "flightDto.maxBaggageWeight")
    @Mapping(target = "isWifiAvailable", source = "flightDto.isWifiAvailable")
    @Mapping(target = "availableSeats", source = "flightDto.availableSeats")
    @Mapping(target = "maxSeats", source = "flightDto.maxSeats")
    @Mapping(target = "flightStatus", constant = "ACTIVE")
    FlightDetailEntity toDetail(FlightDto flightDto);


    @Mapping(target = "gateNumber", source = "flightEntity.flightDetail.gateNumber")
    @Mapping(target = "maxBaggageWeight", source = "flightEntity.flightDetail.maxBaggageWeight")
    @Mapping(target = "isWifiAvailable", source = "flightEntity.flightDetail.isWifiAvailable")
    @Mapping(target = "availableSeats", source = "flightEntity.flightDetail.availableSeats")
    @Mapping(target = "maxSeats", source = "flightEntity.flightDetail.maxSeats")
    FlightDto toDto(FlightEntity flightEntity);

    @Mapping(target = "flightDetail.airCraftModel", source = "request.airCraftModel")
    @Mapping(target = "flightDetail.gateNumber", source = "request.gateNumber")
    @Mapping(target = "flightDetail.maxBaggageWeight", source = "request.maxBaggageWeight")
    @Mapping(target = "flightDetail.isWifiAvailable", source = "request.isWifiAvailable")
    @Mapping(target = "flightDetail.availableSeats", source = "request.availableSeats")
    @Mapping(target = "flightDetail.maxSeats", source = "request.maxSeats")
    @Mapping(target = "flightDetail.flightStatus", source = "request.flightStatus")
    FlightEntity toEnt(CreateFlightRequest request);

    @Mapping(target = "flightDetail.airCraftModel", source = "request.airCraftModel")
    @Mapping(target = "flightDetail.departureTerminal", source = "request.departureTerminal")
    @Mapping(target = "flightDetail.arrivalTerminal", source = "request.arrivalTerminal")
    @Mapping(target = "flightDetail.gateNumber", source = "request.gateNumber")
    @Mapping(target = "flightDetail.maxBaggageWeight", source = "request.maxBaggageWeight")
    @Mapping(target = "flightDetail.isWifiAvailable", source = "request.isWifiAvailable")
    @Mapping(target = "flightDetail.availableSeats", source = "request.availableSeats")
    @Mapping(target = "flightDetail.maxSeats", source = "request.maxSeats")
    @Mapping(target = "flightDetail.flightStatus", source = "request.flightStatus")
    FlightEntity toEnt(@MappingTarget FlightEntity flightEntity, UpdateFlightRequest request);
}
