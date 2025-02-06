package az.perfect.msbookingapp.mapper;

import az.perfect.msbookingapp.domain.entity.FlightDetailEntity;
import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.model.dto.response.FlightDto;
import az.perfect.msbookingapp.model.dto.request.CreateFlightRequest;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements Mapper<FlightEntity, FlightDto> {


    @Override
    public FlightEntity toEnt(FlightDto flightDto) {
        return null;
    }

    @Override
    public FlightDto toDto(FlightEntity flightEntity) {
        return FlightDto.builder()
                .departureCity(flightEntity.getDepartureCity())
                .arrivalCity(flightEntity.getArrivalCity())
                .departureAirport(flightEntity.getDepartureAirport())
                .arrivalAirport(flightEntity.getArrivalAirport())
                .departureTime(flightEntity.getDepartureTime())
                .arrivalTime(flightEntity.getArrivalTime())
                .gateNumber(flightEntity.getFlightDetail().getGateNumber())
                .maxBaggageWeight(flightEntity.getFlightDetail().getMaxBaggageWeight())
                .isWifiAvailable(flightEntity.getFlightDetail().getIsWifiAvailable())
                .availableSeats(flightEntity.getFlightDetail().getAvailableSeats())
                .maxSeats(flightEntity.getFlightDetail().getMaxSeats())
                .build();
    }

    public FlightEntity toEnt(CreateFlightRequest request) {
        FlightDetailEntity flightDetail = new FlightDetailEntity();
        flightDetail.setAirCraftModel(request.getAirCraftModel());
        flightDetail.setDepartureTerminal(request.getDepartureTerminal());
        flightDetail.setArrivalTerminal(request.getArrivalTerminal());
        flightDetail.setGateNumber(request.getGateNumber());
        flightDetail.setMaxBaggageWeight(request.getMaxBaggageWeight());
        flightDetail.setIsWifiAvailable(request.getIsWifiAvailable());
        flightDetail.setAvailableSeats(request.getAvailableSeats());
        flightDetail.setMaxSeats(request.getMaxSeats());
        flightDetail.setFlightStatus(request.getFlightStatus());
        return FlightEntity.builder()
                .airlineName(request.getAirlineName())
                .departureCity(request.getDepartureCity())
                .arrivalCity(request.getArrivalCity())
                .departureAirport(request.getDepartureAirport())
                .arrivalAirport(request.getArrivalAirport())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .flightDetail(flightDetail)
                .build();
    }
}
