package org.technical.exam.priceapi.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.domain.port.PricePersistencePort;
import org.technical.exam.priceapi.domain.service.DefaultProductPriceService;

@SpringBootTest
class DefaultProductPriceServiceTest {

  @Mock
  private PricePersistencePort pricePersistence;

  @InjectMocks
  private DefaultProductPriceService pricingService;

  @Test
  @DisplayName("Should return empty optional when no price applies for the given brand, product, and date")
  void shouldReturnEmptyOptionalWhenNoPriceAppliesForGivenBrandProductAndDate() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    when(pricePersistence.findPriceDetailsByBrandAndProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Collections.emptyList());

    Optional<PriceModel> actualPrice =
        pricingService.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    verify(pricePersistence)
        .findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    assertEquals(Optional.empty(), actualPrice);
  }

  @Test
  @DisplayName("Should return the price for the given brand, product, and date")
  void shouldReturnPriceForGivenBrandProductAndDate() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    PriceModel expectedPrice = mock(PriceModel.class);

    when(pricePersistence.findPriceDetailsByBrandAndProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(List.of(expectedPrice));

    Optional<PriceModel> actualPrice =
        pricingService.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    verify(pricePersistence)
        .findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    assertEquals(Optional.of(expectedPrice), actualPrice);
  }

  @Test
  @DisplayName("Should return the correct price when multiple prices apply for the given brand, product, and date")
  void shouldReturnCorrectPriceForMultiplePricesOnGivenBrandProductAndDate() {

    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    PriceModel expectedPrice = mock(PriceModel.class);

    when(pricePersistence.findPriceDetailsByBrandAndProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(List.of(expectedPrice, mock(PriceModel.class), mock(PriceModel.class)));

    Optional<PriceModel> actualPrice =
        pricingService.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    verify(pricePersistence)
        .findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    assertEquals(Optional.of(expectedPrice), actualPrice);
  }

  @Test
  @DisplayName("Should return an empty optional when one or more parameters are null")
  void shouldReturnEmptyOptionalWhenParametersAreNull() {

    Long brandId = null;
    Long productId = null;
    LocalDateTime applicationDate = null;

    when(pricePersistence.findPriceDetailsByBrandAndProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Collections.emptyList());

    Optional<PriceModel> actualPrice =
        pricingService.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    verify(pricePersistence)
        .findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    assertEquals(Optional.empty(), actualPrice);
  }

  @Test
  void testRetrievePriceForBrandProductAndDateApplication6() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    when(pricePersistence.findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate))
        .thenThrow(RuntimeException.class);

    assertThrows(RuntimeException.class, () ->
        pricingService.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate));
  }
}
