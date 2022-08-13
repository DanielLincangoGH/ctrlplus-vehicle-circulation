package org.conecta.ctrlplus.vehicle.circulation.repository;

import java.util.List;
import org.conecta.ctrlplus.vehicle.circulation.entities.ScheduleHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleHoursRepository extends JpaRepository<ScheduleHoursEntity, Long> {

  @Query("select hours from ScheduleHoursEntity hours "
      + "join hours.restrictionSchedule schedule "
      + "where schedule.digit = :digit and schedule.restrictionDay =:day")
  List<ScheduleHoursEntity> findScheduledHours(String digit, Integer day);

}
