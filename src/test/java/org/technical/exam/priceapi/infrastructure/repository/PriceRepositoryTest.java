package org.technical.exam.priceapi.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.technical.exam.priceapi.infrastructure.entity.Price;

@DataJpaTest
class PriceRepositoryTest {

  @Autowired
  private PriceRepository priceRepository;

  @Test
  @DisplayName("Should return an empty list when no prices are available for the given brand, product, and application date")
  void shouldReturnEmptyListWhenNoPricesAvailableForBrandProductAndApplicationDate() {

    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 13, 0, 0, 0);
    Long brandId = 1L;
    Long productId = 35455L;

    List<Price> priceEntities = priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(
        brandId, productId, applicationDate);

    assertNotNull(priceEntities);
    assertThat(priceEntities).isEmpty();
  }

  @Test
  @DisplayName("Should return a list with a single record when there is only one price available for the brand, product, and specific application date")
  void shouldReturnListWithSingleRecordWhenThereIsOnlyOnePriceForBrandProductAndSpecificApplicationDate() {

    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 9, 0, 0);
    Long brandId = 1L;
    Long productId = 35455L;

    List<Price> priceEntities = priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(
        brandId, productId, applicationDate);

    assertThat(priceEntities)
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  @DisplayName("Should return a list of records when there are multiple prices available for the brand, product, and specific application date")
  void shouldReturnListOfRecordsWhenThereAreMultiplePricesAvailableForBrandProductAndSpecificApplicationDate() {

    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 11, 0, 0);
    Long brandId = 1L;
    Long productId = 35455L;

    List<Price> priceEntities = priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(
        brandId, productId, applicationDate);

    assertThat(priceEntities)
        .isNotEmpty()
        .hasSize(2);
  }

  @Test
  @DisplayName("Should return a list with a price when the application date matches the start date and time of that price")
  void shouldReturnListWithPriceWhenApplicationDateMatchesStartDateTimeOfThatPrice() {

    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
    Long brandId = 1L;
    Long productId = 35455L;

    List<Price> priceEntities = priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(
        brandId, productId, applicationDate);

    assertThat(priceEntities)
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  @DisplayName("Should return empty list when input parameters are null without throwing an exception")
  void shouldReturnEmptyListOnNullValuesWithoutException() {
    assertDoesNotThrow(() -> {
      List<Price> priceEntities = priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(
          null, null, null);

      assertThat(priceEntities).isEmpty();
    });
  }

}
