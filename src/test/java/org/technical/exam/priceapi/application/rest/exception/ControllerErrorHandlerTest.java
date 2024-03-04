package org.technical.exam.priceapi.application.rest.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.technical.exam.priceapi.application.rest.response.ErrorResponse;

@SpringBootTest
class ControllerErrorHandlerTest {

  @InjectMocks
  private ControllerErrorHandler controllerErrorHandler;

  @Test
  @DisplayName("Should respond Internal Server Error when receiving an exception.")
  public void shouldRespondInternalServerErrorWhenReceivingException() {
    Exception genericException = mock(Exception.class);

    ResponseEntity<ErrorResponse> responseEntity = controllerErrorHandler.handleException(genericException);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getBody().getError());
    assertEquals("An error occurred", responseEntity.getBody().getDetail());
  }

  @Test
  @DisplayName("Should respond Internal Server Error when receiving a DataAccessException.")
  void shouldRespondInternalServerErrorWhenReceivingDataAccessException() {
    DataAccessException dataAccessException = mock(DataAccessException.class);

    ResponseEntity<ErrorResponse> responseEntity = controllerErrorHandler.handleDataAccessException(dataAccessException);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getBody().getError());
    assertEquals("An unexpected error occurred", responseEntity.getBody().getDetail());
  }

  @Test
  @DisplayName("Should respond Bad Request when receiving a MissingServletRequestParameterException.")
  void shouldRespondBadRequestWhenReceivingMissingServletRequestParameterException() {
    MissingServletRequestParameterException missingParamException = mock(MissingServletRequestParameterException.class);

    ResponseEntity<ErrorResponse> responseEntity = controllerErrorHandler.handleRequestParameterException(missingParamException);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseEntity.getBody().getError());
    assertEquals(missingParamException.getMessage(), responseEntity.getBody().getDetail());
  }

  @Test
  @DisplayName("Should respond Bad Request when receiving a MethodArgumentTypeMismatchException.")
  void shouldRespondBadRequestWhenReceivingMethodArgumentTypeMismatchException() {
    MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = mock(MethodArgumentTypeMismatchException.class);
    ResponseEntity<ErrorResponse> responseEntity = controllerErrorHandler.handleRequestParameterException(methodArgumentTypeMismatchException);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseEntity.getBody().getError());
    assertEquals(methodArgumentTypeMismatchException.getMessage(), responseEntity.getBody().getDetail());
  }

  @Test
  @DisplayName("Should respond Bad Request when receiving a IllegalArgumentException.")
  void shouldRespondBadRequestWhenReceivingIllegalArgumentException() {
    IllegalArgumentException illegalArgumentException = mock(IllegalArgumentException.class);
    ResponseEntity<ErrorResponse> responseEntity = controllerErrorHandler.handleRequestParameterException(illegalArgumentException);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseEntity.getBody().getError());
    assertEquals(illegalArgumentException.getMessage(), responseEntity.getBody().getDetail());
  }

}
