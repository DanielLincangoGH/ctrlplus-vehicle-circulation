package org.conecta.ctrlplus.vehicle.circulation.service.impl;

import static org.conecta.ctrlplus.vehicle.circulation.utils.RestrictionUtils.hasCirculationRestriction;
import static org.conecta.ctrlplus.vehicle.circulation.utils.RestrictionUtils.resolveLastDigit;

import java.time.LocalDateTime;
import org.conecta.ctrlplus.vehicle.circulation.dto.Circulation;
import org.conecta.ctrlplus.vehicle.circulation.dto.Restriction;
import org.conecta.ctrlplus.vehicle.circulation.repository.ScheduleHoursRepository;
import org.conecta.ctrlplus.vehicle.circulation.service.RestrictionService;
import org.conecta.ctrlplus.vehicle.circulation.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class RestrictionServiceImpl implements RestrictionService {

  @Autowired
  private ScheduleHoursRepository scheduleHoursRepository;

  @Autowired
  private VehicleService vehicleService;

  @Override
  public Circulation validateCirculationRestriction(String plateId, LocalDateTime dateTime) {

    final var scheduleHours = scheduleHoursRepository
        .findScheduledHours(resolveLastDigit(plateId), dateTime.getDayOfWeek().getValue());

    var circulationResponse = Circulation.builder()
        .vehicle(vehicleService.findByPlateId(plateId));

    var restriction = Restriction.builder();

    if (CollectionUtils.isEmpty(scheduleHours)) {
      restriction.hasRestriction(false);
      return circulationResponse.restriction(restriction.build()).build();
    }

    restriction.hasRestriction(hasCirculationRestriction(dateTime.toLocalTime(), scheduleHours));
    return circulationResponse.restriction(restriction.build()).build();

  }

}
