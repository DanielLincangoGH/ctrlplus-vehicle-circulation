package org.conecta.ctrlplus.vehicle.circulation.service;

import static java.text.MessageFormat.format;
import static org.conecta.ctrlplus.vehicle.circulation.utils.messages.VehicleMessages.ALREADY_REGISTERED_MSG_FORMAT;
import static org.conecta.ctrlplus.vehicle.circulation.utils.messages.VehicleMessages.SUCCESS_REGISTRATION_MSG;
import static org.conecta.ctrlplus.vehicle.circulation.utils.messages.VehicleMessages.VEHICLE_IS_NOT_REGISTERED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.entities.VehicleEntity;
import org.conecta.ctrlplus.vehicle.circulation.mapper.VehicleMapper;
import org.conecta.ctrlplus.vehicle.circulation.repository.VehicleRepository;
import org.conecta.ctrlplus.vehicle.circulation.service.impl.VehicleServiceImpl;
import org.conecta.ctrlplus.vehicle.circulation.utils.exception.VehicleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

  @InjectMocks
  private VehicleServiceImpl vehicleService;

  @Mock
  private VehicleRepository vehicleRepository;

  @Mock
  private VehicleMapper vehicleMapper;

  private VehicleEntity dummyVehicle;

  @BeforeEach
  void setUp() {
    dummyVehicle = VehicleEntity.builder().id(1L).build();
  }

  @Test
  @DisplayName("Given new vehicle when register then return new vehicle id")
  void givenNewVehicleWhenRegisterThenReturnNewVehicleId() {

    when(vehicleRepository.findByPlateId(anyString())).thenReturn(Optional.empty());
    when(vehicleMapper.toEntity(any())).thenReturn(dummyVehicle);
    when(vehicleRepository.save(any())).thenReturn(dummyVehicle);

    final var creation = vehicleService.register(Vehicle.builder().plateId("any").build());

    assertEquals(1L, creation.getVehicle().getId());
    assertEquals(SUCCESS_REGISTRATION_MSG, creation.getMessage().getMessage());

  }

  @Test
  @DisplayName("Given new vehicle when already registered then return registered vehicle id")
  void givenNewVehicleWhenAlreadyRegisteredThenReturnRegisteredVehicleId() {

    when(vehicleRepository.findByPlateId(anyString())).thenReturn(Optional.of(dummyVehicle));

    final var newVehicle = Vehicle.builder().plateId("any").build();
    final var creation = vehicleService.register(newVehicle);

    final var message = format(ALREADY_REGISTERED_MSG_FORMAT, newVehicle.getPlateId());

    assertEquals(1L, creation.getVehicle().getId());
    assertEquals(message, creation.getMessage().getMessage());

    verify(vehicleMapper, times(0)).toEntity(any());
    verify(vehicleRepository, times(0)).save(any());

  }

  @Test
  @DisplayName("Given plate id when vehicle is not registered then throws exception")
  void givenPlateIdWhenVehicleIsNotRegisteredThenThrowsException() {

    when(vehicleRepository.findByPlateId(anyString())).thenReturn(Optional.empty());

    VehicleException exception = assertThrows(VehicleException.class,
        () -> vehicleService.findByPlateId("any"));

    assertEquals(VEHICLE_IS_NOT_REGISTERED, exception.getMessage());

  }

  @Test
  @DisplayName("Given plate id when vehicle is registered then returns vehicle info")
  void givenPlateIdWhenVehicleIsRegisteredThenReturnsVehicleInfo() {

    when(vehicleRepository.findByPlateId(anyString())).thenReturn(Optional.of(dummyVehicle));
    when(vehicleMapper.toDto(any())).thenReturn(Vehicle.builder().id(1L).build());

    final var vehicle = vehicleService.findByPlateId("any");

    assertEquals(1L, vehicle.getId());

  }

}