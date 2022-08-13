package org.conecta.ctrlplus.vehicle.circulation.entities;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "restriction_schedule")
public class RestrictionScheduleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_restriction_schedule", nullable = false)
  private Long id;

  @Column(name = "digit", nullable = false, length = 1)
  private String digit;

  @Column(name = "restriction_day", nullable = false)
  private Integer restrictionDay;

  @Column(name = "status", nullable = false)
  private int status;

  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @OneToMany(mappedBy = "restrictionSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<ScheduleHoursEntity> scheduleHours;

}
