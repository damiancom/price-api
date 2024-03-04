package org.technical.exam.priceapi.application.rest.controller;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;
import org.technical.exam.priceapi.domain.port.PriceControllerPort;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/prices")
public class PriceController {

  final PriceControllerPort priceAdapter;

  @GetMapping("/")
  public ResponseEntity<PriceResponse> getPrice(
      @RequestParam("brand_id") @NonNull Long brandId,
      @RequestParam("product_id") @NonNull Long productId,
      @RequestParam("application_date") @NonNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

    return priceAdapter
        .retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate)
        .map(price -> new ResponseEntity<>(price, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
