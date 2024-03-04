package org.technical.exam.priceapi.application.rest.controller.integration;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.technical.exam.priceapi.application.rest.controller.PriceController;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PriceControllerTest {

  @Autowired
  private PriceController priceController;

  @Test
  @Transactional
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt1000OnDay14ForProduct35455AndBrand1() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getPrice()).isEqualTo(35.50);
  }

  @Test
  @Transactional
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt1600OnDay14ForProduct35455AndBrand1() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getPrice()).isEqualTo(25.45);
  }

  @Test
  @Transactional
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt2100OnDay14ForProduct35455AndBrand1() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getPrice()).isEqualTo(35.50);
  }

  @Test
  @Transactional
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt1000OnDay15ForProduct35455AndBrand1() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getPrice()).isEqualTo(30.50);
  }

  @Test
  @Transactional
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt2100OnDay16ForProduct35455AndBrand1() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getPrice()).isEqualTo(38.95);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when receiving productId, brandId, and date with no applicable price")
  void shouldReturnStatus400BadRequestWhenReceivingProductIdBrandIdAndDateWithNoApplicablePrice() {
    LocalDateTime applicationDate = LocalDateTime.of(2022, 6, 16, 21, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when applicationDate is null")
  void shouldReturnStatus400BadRequestWhenApplicationDateIsNull() {
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, 35455L, null);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when productId is null")
  void shouldReturnStatus400BadRequestWhenProductIdIsNull() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(1L, null, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when brandId is null")
  void shouldReturnStatus400BadRequestWhenBrandIdIsNull() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
    ResponseEntity<PriceResponse> response = priceController.getPrice(null, 35455L, applicationDate);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when all parameters are null")
  void shouldReturnStatus400BadRequestWhenAllParametersAreNull() {
    ResponseEntity<PriceResponse> response = priceController.getPrice(null, null, null);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

}
