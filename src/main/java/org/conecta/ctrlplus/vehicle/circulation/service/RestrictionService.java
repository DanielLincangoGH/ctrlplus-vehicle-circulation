package org.conecta.ctrlplus.vehicle.circulation.service;

import java.time.LocalDateTime;
import org.conecta.ctrlplus.vehicle.circulation.dto.Circulation;

public interface RestrictionService {

  Circulation validateCirculationRestriction(String plateId, LocalDateTime dateTime);
}
