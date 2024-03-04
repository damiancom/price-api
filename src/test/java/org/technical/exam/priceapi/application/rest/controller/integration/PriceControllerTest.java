package org.technical.exam.priceapi.application.rest.controller.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;
import org.technical.exam.priceapi.PriceApiApplication;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = PriceApiApplication.class)
@SpringJUnitConfig
@TestPropertySource(locations = "classpath:integration-test-application.properties")
class PriceControllerTest {

  @Value("${server.port}")
  private int port;

  @Value("${api.version}")
  private String apiVersion;

  private static final String FORMAT_QUERY_PARAMS = "?brand_id={id}&product_id={productId}&application_date={applicationDate}";

  private static final String FORMAT_URI = "/api/%s/prices";

  private final RestTemplate restTemplate = new TestRestTemplate("user", "pass").getRestTemplate();

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt1000OnDay14ForProduct35455AndBrand1() {

    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

    Double priceExpected = 35.50;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertNotNull(response);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(brandId, response.getBody().getBrandId());
    assertEquals(priceExpected, response.getBody().getPrice());
    assertEquals(productId, response.getBody().getProductId());
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt1600OnDay14ForProduct35455AndBrand1() {

    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);

    Double priceExpected = 25.45;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertNotNull(response);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(brandId, response.getBody().getBrandId());
    assertEquals(priceExpected, response.getBody().getPrice());
    assertEquals(productId, response.getBody().getProductId());
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt2100OnDay14ForProduct35455AndBrand1() {

    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);

    Double priceExpected = 35.50;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertNotNull(response);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(brandId, response.getBody().getBrandId());
    assertEquals(priceExpected, response.getBody().getPrice());
    assertEquals(productId, response.getBody().getProductId());
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt1000OnDay15ForProduct35455AndBrand1() {

    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);

    Double priceExpected = 30.50;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertNotNull(response);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(brandId, response.getBody().getBrandId());
    assertEquals(priceExpected, response.getBody().getPrice());
    assertEquals(productId, response.getBody().getProductId());
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
  void shouldReturnCorrectPriceForRequestAt2100OnDay16ForProduct35455AndBrand1() {

    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);

    Double priceExpected = 38.95;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertNotNull(response);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(brandId, response.getBody().getBrandId());
    assertEquals(priceExpected, response.getBody().getPrice());
    assertEquals(productId, response.getBody().getProductId());
  }

  @Test
  @Transactional
  @DisplayName("Should return status 204 No content when receiving productId, brandId, and date with no applicable price")
  void shouldReturnStatus204NoContentWhenReceivingProductIdBrandIdAndDateWithNoApplicablePrice() {
    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2022, 6, 16, 21, 0);

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when applicationDate is null")
  void shouldReturnStatus400BadRequestWhenApplicationDateIsNull() {
    Long brandId = 1L;
    Long productId = 35455L;
    LocalDateTime applicationDate = null;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when productId is null")
  void shouldReturnStatus400BadRequestWhenProductIdIsNull() {

    Long brandId = 1L;
    Long productId = null;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when brandId is null")
  void shouldReturnStatus400BadRequestWhenBrandIdIsNull() {
    Long brandId = null;
    Long productId = 35455L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when all parameters are null")
  void shouldReturnStatus400BadRequestWhenAllParametersAreNull() {
    Long brandId = null;
    Long productId = null;
    LocalDateTime applicationDate = null;

    String uri = String.format(FORMAT_URI, apiVersion);

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @Transactional
  @DisplayName("Should return status 400 Bad Request when all parameters are null")
  void shouldReturnStatus400BadRequestWhenAllParametersAreNulls() {
    Long brandId = null;
    Long productId = null;
    LocalDateTime applicationDate = null;

    String uri = String.format(FORMAT_URI, "error");

    final ResponseEntity<PriceResponse> response = restTemplate.getForEntity(getBaseUrl() + uri + FORMAT_QUERY_PARAMS,
        PriceResponse.class, brandId, productId, applicationDate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

}
