package org.conecta.ctrlplus.vehicle.circulation.dto;

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

  @NotBlank()
  private String plateId;

  @NotBlank()
  private String brand;

  @NotBlank()
  private String model;

  @NotNull()
  private Integer manufacturingYear;

  @NotBlank()
  private String chassis;

  @NotBlank()
  private String engine;

}
