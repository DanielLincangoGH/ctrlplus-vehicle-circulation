package org.conecta.ctrlplus.vehicle.circulation.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluateRestriction {

  @NotNull(message = "La fecha es requerida")
  private String evaluationDate;

}
