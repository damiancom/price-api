package org.technical.exam.priceapi.application.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponse {

  @JsonProperty("brand_id")
  Long brandId;
  @JsonProperty("product_id")
  Long productId;
  @JsonProperty("price_id")
  Long priceId;
  Double price;
  @JsonProperty("start_date")
  LocalDateTime startDate;
  @JsonProperty("end_date")
  LocalDateTime endDate;

}
