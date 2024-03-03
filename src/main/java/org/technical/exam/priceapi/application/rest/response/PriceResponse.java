package org.technical.exam.priceapi.application.rest.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponse {

  Long brandId;
  Long productId;
  Long priceId;
  Double price;
  LocalDateTime startDate;
  LocalDateTime endDate;

}
