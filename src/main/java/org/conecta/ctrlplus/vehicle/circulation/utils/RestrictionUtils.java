package org.conecta.ctrlplus.vehicle.circulation.utils;

import java.time.LocalTime;
import java.util.List;
import org.conecta.ctrlplus.vehicle.circulation.entities.ScheduleHoursEntity;

public final class RestrictionUtils {

  private RestrictionUtils() {
  }

  public static String resolveLastDigit(String plateId) {
    return plateId.substring(plateId.length() - 1);
  }

  public static boolean hasCirculationRestriction(LocalTime currentTime,
      List<ScheduleHoursEntity> scheduleHours) {
    return scheduleHours.stream()
        .anyMatch(scheduleHoursEntity -> isBetweenRestrictedHour(currentTime, scheduleHoursEntity));
  }

  private static boolean isBetweenRestrictedHour(LocalTime currentHour,
      ScheduleHoursEntity scheduledHour) {
    return currentHour.isAfter(scheduledHour.getStartHour())
        && currentHour.isBefore(scheduledHour.getEndHour());
  }

}
