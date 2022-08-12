package org.conecta.ctrlplus.vehicle.circulation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Circulation {

  private Vehicle vehicle;

  private Restriction restriction;

}
