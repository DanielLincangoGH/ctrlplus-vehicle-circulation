package org.conecta.ctrlplus.vehicle.circulation.service.impl;

import static java.text.MessageFormat.format;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.ALREADY_REGISTERED_MSG_FORMAT;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.SUCCESS_REGISTRATION_MSG;

import org.conecta.ctrlplus.vehicle.circulation.dto.Message;
import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.dto.VehicleCreate;
import org.conecta.ctrlplus.vehicle.circulation.mapper.VehicleMapper;
import org.conecta.ctrlplus.vehicle.circulation.repository.VehicleRepository;
import org.conecta.ctrlplus.vehicle.circulation.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private VehicleMapper vehicleMapper;

  @Override
  public VehicleCreate register(Vehicle vehicle) {

    final var registeredVehicle = vehicleRepository.findByPlateId(vehicle.getPlateId());

    if (registeredVehicle.isPresent()) {
      vehicle.setId(registeredVehicle.get().getId());
      final var message = format(ALREADY_REGISTERED_MSG_FORMAT, vehicle.getPlateId());
      return VehicleCreate.builder()
          .vehicle(vehicle)
          .message(Message.builder()
              .message(message).build()).build();
    }

    final var newVehicle = vehicleMapper.toEntity(vehicle);
    vehicleRepository.save(newVehicle);
    vehicle.setId(newVehicle.getId());

    return VehicleCreate.builder()
        .vehicle(vehicle)
        .message(Message.builder()
            .message(SUCCESS_REGISTRATION_MSG).build()).build();
  }

}
