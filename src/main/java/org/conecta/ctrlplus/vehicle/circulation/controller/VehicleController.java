package org.conecta.ctrlplus.vehicle.circulation.controller;

import javax.validation.Valid;
import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.dto.VehicleCreate;
import org.conecta.ctrlplus.vehicle.circulation.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicle/api")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @PostMapping("/v1")
  public VehicleCreate register(@RequestBody @Valid Vehicle vehicle) {
    return this.vehicleService.register(vehicle);
  }

}
