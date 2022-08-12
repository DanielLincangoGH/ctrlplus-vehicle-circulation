package org.conecta.ctrlplus.vehicle.circulation.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restriction_schedule")
public class RestrictionScheduleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "digit", nullable = false, length = 1)
  private String digit;

  @Column(name = "restriction_day", nullable = false)
  private Integer restriction_day;

  @Column(name = "start_hour", nullable = false)
  private Integer startHour;

  @Column(name = "end_hour", nullable = false)
  private Integer endHour;

  @Column(name = "create_time", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
  private LocalDateTime create_time;

}
