package org.conecta.ctrlplus.vehicle.circulation.repository;

import java.util.Optional;
import org.conecta.ctrlplus.vehicle.circulation.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {

  Optional<VehicleEntity> findByPlateId(String plateId);

}
