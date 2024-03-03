package org.technical.exam.priceapi.domain.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceModel {

  Long id;
  LocalDateTime startDate;
  LocalDateTime endDate;
  Long priceList;
  Long productId;
  Integer priority;
  Double price;
  String curr;
  BrandModel brand;

}
