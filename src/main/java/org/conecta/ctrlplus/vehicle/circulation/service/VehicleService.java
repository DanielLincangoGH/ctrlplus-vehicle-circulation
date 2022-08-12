package org.conecta.ctrlplus.vehicle.circulation.service;

import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.dto.VehicleCreate;

public interface VehicleService {

  VehicleCreate register(Vehicle vehicle);

  Vehicle findByPlateId(String plateId);

}

