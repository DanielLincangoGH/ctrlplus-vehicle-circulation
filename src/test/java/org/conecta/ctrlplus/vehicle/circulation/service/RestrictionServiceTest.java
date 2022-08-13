package org.conecta.ctrlplus.vehicle.circulation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.entities.ScheduleHoursEntity;
import org.conecta.ctrlplus.vehicle.circulation.repository.ScheduleHoursRepository;
import org.conecta.ctrlplus.vehicle.circulation.service.impl.RestrictionServiceImpl;
import org.conecta.ctrlplus.vehicle.circulation.utils.messages.CirculationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestrictionServiceTest {

  @InjectMocks
  private RestrictionServiceImpl restrictionService;

  @Mock
  private VehicleService vehicleService;

  @Mock
  private ScheduleHoursRepository scheduleHoursRepository;

  private Vehicle dummyVehicle;

  private LocalDateTime dummyTime;

  private List<ScheduleHoursEntity> dummyScheduleHours;


  @BeforeEach
  void setUp() {
    dummyVehicle = Vehicle.builder().plateId("anyPlateId").build();
    dummyTime = LocalDateTime.of(LocalDate.of(2022, 8, 12), LocalTime.of(9, 0, 0));
    dummyScheduleHours = buildScheduleHours();
  }


  @Test
  @DisplayName("Given plate id and date time when schedule is not configured then return vehicle")
  void givenPlateIdAndDateTimeWhenScheduleIsNotConfiguredThenReturnVehicle() {

    when(scheduleHoursRepository.findScheduledHours(anyString(), anyInt()))
        .thenReturn(new ArrayList<>());

    when(vehicleService.findByPlateId(anyString())).thenReturn(dummyVehicle);

    final var circulation = restrictionService
        .evaluateCirculationRestriction("anyPlateId", dummyTime);

    assertEquals("anyPlateId", circulation.getVehicle().getPlateId());
    assertFalse(circulation.getRestriction().isHasRestriction());
    assertNull(circulation.getRestriction().getMessage());

  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4, 9, 10, 11, 12, 13, 14, 15, 22, 23})
  @DisplayName("Given plate id and date when not has restriction then return vehicle info")
  void givenPlateIdAndDateTimeWhenNotHasRestrictionThenReturnVehicleInfo(int hour) {

    dummyTime = LocalDateTime.of(LocalDate.of(2022, 8, 12), LocalTime.of(hour, 30, 0));

    when(scheduleHoursRepository.findScheduledHours(anyString(), anyInt()))
        .thenReturn(dummyScheduleHours);

    when(vehicleService.findByPlateId(anyString())).thenReturn(dummyVehicle);

    final var circulation = restrictionService
        .evaluateCirculationRestriction("anyPlateId", dummyTime);

    assertEquals("anyPlateId", circulation.getVehicle().getPlateId());
    assertFalse(circulation.getRestriction().isHasRestriction());
    assertNull(circulation.getRestriction().getMessage());

  }

  @ParameterizedTest
  @ValueSource(ints = {7, 8, 9, 17, 18, 19, 20})
  @DisplayName("Given plate id and date when not has restriction then return vehicle info")
  void givenPlateIdAndDateTimeWhenIsAllowedToDrivingThenReturnVehicleAndSuccessMessage(int hour) {

    dummyTime = LocalDateTime.of(LocalDate.of(2022, 8, 12), LocalTime.of(hour, 0, 0));

    when(scheduleHoursRepository.findScheduledHours(anyString(), anyInt()))
        .thenReturn(dummyScheduleHours);

    when(vehicleService.findByPlateId(anyString())).thenReturn(dummyVehicle);

    final var circulation = restrictionService
        .evaluateCirculationRestriction("anyPlateId", dummyTime);

    assertEquals("anyPlateId", circulation.getVehicle().getPlateId());
    assertTrue(circulation.getRestriction().isHasRestriction());
    assertNotNull(circulation.getRestriction().getMessage());
    assertEquals(CirculationMessages.RESTRICTION_MSG, circulation.getRestriction().getMessage());

  }

  private static List<ScheduleHoursEntity> buildScheduleHours() {
    return List.of(
        ScheduleHoursEntity.builder()
            .startHour(LocalTime.of(6, 0))
            .endHour(LocalTime.of(9, 30)).build(),
        ScheduleHoursEntity.builder()
            .startHour(LocalTime.of(16, 0))
            .endHour(LocalTime.of(21, 0)).build());
  }

}