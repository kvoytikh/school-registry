package com.test.utility;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatusCode status,
          WebRequest request) {
    List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(this::getErrorMessage)
            .toList();
   return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(
          EntityNotFoundException ex, WebRequest request) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, List.of(ex.getMessage()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrityViolationException(
          DataIntegrityViolationException ex, WebRequest request) {
    String rootMessage = ex.getMostSpecificCause().getMessage();
    String customMessage;

    if (rootMessage.contains("Duplicate entry") && rootMessage.contains("edrpou")) {
      customMessage = "A school with this EDRPOU already exists.";
    } else {
      customMessage = "Data integrity violation: " + rootMessage;
    }
    return buildErrorResponse(HttpStatus.BAD_REQUEST, List.of(customMessage));
  }

  private String getErrorMessage(ObjectError e) {
    if (e instanceof FieldError fieldError) {
      String field = fieldError.getField();
      String message = e.getDefaultMessage();
      return field + " " + message;
    }
    return e.getDefaultMessage();
  }

  private ResponseEntity<Object> buildErrorResponse(HttpStatus status, List<String> message) {
    Map<String, Object> body = createErrorResponse(status, message);
    return new ResponseEntity<>(body, status);
  }

  private Map<String, Object> createErrorResponse(HttpStatus status, List<String> messages) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", messages);
    body.put("status", status.value());
    return body;
  }
}
