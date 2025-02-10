package az.perfect.msbookingapp.service;

import az.perfect.msbookingapp.domain.entity.FlightEntity;
import az.perfect.msbookingapp.domain.entity.UserEntity;
import az.perfect.msbookingapp.domain.repository.FlightRepository;
import az.perfect.msbookingapp.domain.repository.UserEntityRepository;
import az.perfect.msbookingapp.exception.AlreadyExists;
import az.perfect.msbookingapp.exception.NotFoundException;
import az.perfect.msbookingapp.exception.TimeException;
import az.perfect.msbookingapp.exception.UnauthorizedAccessException;
import az.perfect.msbookingapp.mapper.FlightMapper;
import az.perfect.msbookingapp.model.dto.response.FlightDto;
import az.perfect.msbookingapp.model.dto.request.CreateFlightRequest;
import az.perfect.msbookingapp.model.enums.Role;
import az.perfect.msbookingapp.model.enums.Status;
import az.perfect.msbookingapp.model.dto.request.UpdateFlightRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightService {
    private final FlightRepository flightRepository;
    private final UserEntityRepository userRepository;
    private final FlightMapper flightMapper;

    @Transactional
    public FlightDto create(CreateFlightRequest request, Long adminId) {
        validateAdmin(adminId);
        if (request.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new TimeException("Time does not right!");
        }
        boolean isFlightExists = flightRepository
                .existsByAirlineNameAndDepartureCityAndArrivalCityAndDepartureAirportAndArrivalAirportAndDepartureTime
                        (request.getAirlineName(), request.getDepartureCity(), request.getArrivalCity(),
                                request.getDepartureAirport(), request.getArrivalAirport(), request.getDepartureTime());
        if (isFlightExists) {
            throw new AlreadyExists("This flight has already exists!");
        }
        FlightEntity flightEntity = flightMapper.toEnt(request);
        flightEntity.setCreatedBy(adminId);
        flightEntity.setUpdatedBy(adminId);
        flightEntity.getFlightDetail().setCreatedBy(adminId);
        flightEntity.getFlightDetail().setUpdatedBy(adminId);
        FlightEntity savedEntity = flightRepository.save(flightEntity);
        return flightMapper.toDto(savedEntity);
    }

    public Page<FlightDto> getAllFlight(Pageable pageable) {
        Page<FlightEntity> entityPage = flightRepository.findAll(pageable);
        return entityPage.map(flightMapper::toDto);
    }

    @Transactional
    public void deleteFlightById(Long adminId, Long flightId) {
        validateAdmin(adminId);
        FlightEntity deletedFlight = flightFindById(flightId);
        deletedFlight.getFlightDetail().setFlightStatus(Status.DELETE);
        flightRepository.save(deletedFlight);
    }

    @Transactional
    public FlightDto updateFlight(Long adminId, Long flightId, UpdateFlightRequest updateFlightRequest) {
        validateAdmin(adminId);
        FlightEntity flightEntity = flightFindById(flightId);
        flightEntity = flightMapper.toEnt(flightEntity, updateFlightRequest);
        flightEntity.setUpdatedBy(adminId);
        flightEntity.getFlightDetail().setUpdatedBy(adminId);
        FlightEntity updatedFlight = flightRepository.save(flightEntity);
        return flightMapper.toDto(updatedFlight);
    }

    public FlightDto getById(Long flightId) {
        FlightEntity flight = flightFindById(flightId);
        return flightMapper.toDto(flight);
    }

    public void validateAdmin(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with " + id));
        if (!(user.getRole().equals(Role.ADMIN))) {
            throw new UnauthorizedAccessException("User role is not Admin!");
        }
    }

    public FlightEntity flightFindById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("Flight not found with " + flightId));

    }
}