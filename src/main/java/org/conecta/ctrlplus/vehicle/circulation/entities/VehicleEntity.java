package org.conecta.ctrlplus.vehicle.circulation.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class VehicleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "brand", nullable = false)
  private String brand;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "manufacturing_year", nullable = false)
  private Integer manufacturingYear;

  @Column(name = "chassis", nullable = false)
  private String chassis;

  @Column(name = "engine", nullable = false)
  private String engine;

  @Column(name = "create_time", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
  private LocalDateTime create_time;

  @Column(name = "update_time", nullable = false)
  private LocalDateTime update_time;

}
