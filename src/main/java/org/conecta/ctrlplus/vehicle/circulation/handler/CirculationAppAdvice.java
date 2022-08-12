package org.conecta.ctrlplus.vehicle.circulation.handler;

import static org.conecta.ctrlplus.vehicle.circulation.utils.messages.AppMessages.ERROR_GENERIC_MSG;

import lombok.extern.slf4j.Slf4j;
import org.conecta.ctrlplus.vehicle.circulation.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CirculationAppAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Message handleException(Exception e) {
    log.error("Error in app: {}", e.getMessage(), e);
    return Message.builder().message(ERROR_GENERIC_MSG).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Message handleException(MethodArgumentNotValidException e) {
    log.warn("Validation exception: {}", e.getMessage(), e);
    String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    return Message.builder().message(message).build();
  }

}
