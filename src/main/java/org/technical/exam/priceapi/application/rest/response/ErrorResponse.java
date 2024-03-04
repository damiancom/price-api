package org.technical.exam.priceapi.application.rest.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

  Integer status;
  String error;
  String detail;

}
