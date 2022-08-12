package org.conecta.ctrlplus.vehicle.circulation.dto;

import static org.conecta.ctrlplus.vehicle.circulation.utils.messages.CirculationMessages.RESTRICTION_MSG;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restriction {

  @JsonProperty("hasRestriction")
  private boolean hasRestriction;

  @Getter(AccessLevel.NONE)
  private String message;

  public String getMessage() {
    return this.hasRestriction ? RESTRICTION_MSG : this.message;
  }


}
