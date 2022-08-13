package org.conecta.ctrlplus.vehicle.circulation.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "schedule_hours")
public class ScheduleHoursEntity {

  @Id
  @Column(name = "id_schedule_hours", nullable = false)
  private Long id;

  @Column(name = "start_hour", nullable = false)
  private LocalTime startHour;

  @Column(name = "end_hour", nullable = false)
  private LocalTime endHour;

  @Column(name = "status", nullable = false)
  private int status;

  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @JoinColumn(name = "id_restriction_schedule")
  @ManyToOne(fetch = FetchType.LAZY)
  private RestrictionScheduleEntity restrictionSchedule;

}
