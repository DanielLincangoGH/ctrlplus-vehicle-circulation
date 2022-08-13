package org.conecta.ctrlplus.vehicle.circulation.controller;

import static java.time.LocalDateTime.parse;

import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.conecta.ctrlplus.vehicle.circulation.dto.Circulation;
import org.conecta.ctrlplus.vehicle.circulation.dto.EvaluateRestriction;
import org.conecta.ctrlplus.vehicle.circulation.service.RestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/vehicle/restrictions/api")
public class RestrictionController {

  @Autowired
  private RestrictionService restrictionService;

  @PostMapping("/{plateId}/v1")
  private Circulation evaluateCirculationRestriction(@PathVariable String plateId,
      @RequestBody EvaluateRestriction evaluateRestriction) {
    final var evaluationDate = evaluateRestriction.getEvaluationDate();
    log.info("Evaluation date: {}", evaluationDate);
    final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    final var formattedDate = parse(evaluateRestriction.getEvaluationDate(), formatter);
    return this.restrictionService.evaluateCirculationRestriction(plateId, formattedDate);
  }

}
