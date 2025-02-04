package az.perfect.msbookingapp.service;

import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.repository.FlightRepository;
import az.perfect.msbookingapp.mapper.FlightMapper;
import az.perfect.msbookingapp.model.dto.FlightDto;
import az.perfect.msbookingapp.model.request.CreateFlightRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;


    public FlightDto create(CreateFlightRequest request) {
        FlightEntity flightEntity = flightMapper.toEnt(request);
        FlightEntity savedEntity = flightRepository.save(flightEntity);
        return flightMapper.toDto(savedEntity);
    }
}
