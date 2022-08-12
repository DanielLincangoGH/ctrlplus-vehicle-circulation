package org.conecta.ctrlplus.vehicle.circulation.dto;

import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.BRAND_REQUIRED;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.CHASSIS_REQUIRED;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.ENGINE_REQUIRED;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.MODEL_REQUIRED;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.PLATE_ID_REQUIRED;
import static org.conecta.ctrlplus.vehicle.circulation.utils.VehicleMessages.YEAR_REQUIRED;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

  private Long id;

  @NotBlank(message = PLATE_ID_REQUIRED)
  private String plateId;

  @NotBlank(message = BRAND_REQUIRED)
  private String brand;

  @NotBlank(message = MODEL_REQUIRED)
  private String model;

  @NotNull(message = YEAR_REQUIRED)
  private Integer manufacturingYear;

  @NotBlank(message = CHASSIS_REQUIRED)
  private String chassis;

  @NotBlank(message = ENGINE_REQUIRED)
  private String engine;

}
