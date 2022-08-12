package org.conecta.ctrlplus.vehicle.circulation.repository;

import org.conecta.ctrlplus.vehicle.circulation.entities.RestrictionScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestrictionScheduleRepository extends
    JpaRepository<RestrictionScheduleEntity, Integer> {

}
