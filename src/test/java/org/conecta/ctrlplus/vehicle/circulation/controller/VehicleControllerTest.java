package org.conecta.ctrlplus.vehicle.circulation.controller;

import static org.conecta.ctrlplus.vehicle.circulation.utils.messages.VehicleMessages.SUCCESS_REGISTRATION_MSG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.conecta.ctrlplus.vehicle.circulation.dto.Message;
import org.conecta.ctrlplus.vehicle.circulation.dto.Vehicle;
import org.conecta.ctrlplus.vehicle.circulation.dto.VehicleCreate;
import org.conecta.ctrlplus.vehicle.circulation.service.VehicleService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private VehicleService vehicleService;

  @Test
  @DisplayName("Given new vehicle when info is required then return bad request")
  void givenNewVehicleWhenInfoIsRequiredThenReturnBadRequest() throws Exception {
    when(vehicleService.register(any())).thenReturn(VehicleCreate.builder()
        .vehicle(Vehicle.builder()
            .id(1L).build())
        .message(Message.builder()
            .message(SUCCESS_REGISTRATION_MSG).build()).build());

    mockMvc.perform(post("/vehicle/api/v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(Vehicle.builder().build())))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Given new vehicle when register by API then return new vehicleId")
  void givenNewVehicleWhenRegisterByApiThenReturnNewVehicleId() throws Exception {
    when(vehicleService.register(any())).thenReturn(VehicleCreate.builder()
        .vehicle(Vehicle.builder()
            .id(1L).build())
        .message(Message.builder()
            .message(SUCCESS_REGISTRATION_MSG).build()).build());

    Vehicle vehicle = Vehicle.builder()
        .plateId("anyPlateId")
        .brand("anyBrand")
        .model("anyModel")
        .chassis("anyChassis")
        .engine("anyEngine")
        .manufacturingYear(2000).build();

    mockMvc.perform(post("/vehicle/api/v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(vehicle)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message.message", Matchers.is(SUCCESS_REGISTRATION_MSG)));
  }

}