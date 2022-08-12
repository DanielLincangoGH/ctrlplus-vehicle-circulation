package org.conecta.ctrlplus.vehicle.circulation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreate {

  private Vehicle vehicle;

  private Message message;

}
