package org.conecta.ctrlplus.vehicle.circulation.mapper;

import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.entities.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

  VehicleEntity toEntity(Vehicle dto);

  Vehicle toDto(VehicleEntity entity);

}
