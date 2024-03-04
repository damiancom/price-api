package org.technical.exam.priceapi.application.rest.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;
import org.technical.exam.priceapi.domain.model.BrandModel;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.domain.service.ProductPriceService;

@SpringBootTest
class PriceControllerAdapterTest {

  @InjectMocks
  private PriceControllerAdapter priceControllerAdapter;

  @Mock
  private ProductPriceService priceService;

  @Test
  @DisplayName("Should return an empty Optional when given null parameters.")
  void shouldReturnEmptyOptionalWhenGivenNullParameters() {

    when(priceService.retrievePriceForBrandProductAndDateApplication(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Optional.empty());

    Optional<PriceResponse> priceResponse = priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(null, null, null);

    verify(priceService).retrievePriceForBrandProductAndDateApplication(null, null, null);
    assertNotNull(priceResponse);
    assertTrue(priceResponse.isEmpty());

  }

  @Test
  @DisplayName("Should return an empty Optional when given null parameters and the service responds with null.")
  void shouldReturnEmptyOptionalWhenGivenNullParametersAndServiceRespondsWithNull() {


    when(priceService.retrievePriceForBrandProductAndDateApplication(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(null);

    Optional<PriceResponse> priceResponse = priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(null, null, null);

    verify(priceService).retrievePriceForBrandProductAndDateApplication(null, null, null);
    assertNotNull(priceResponse);
    assertTrue(priceResponse.isEmpty());

  }

  @Test
  @DisplayName("Should return an empty Optional when the service responds with an empty Optional for valid parameters.")
  void shouldReturnEmptyOptionalWhenServiceRespondsWithEmptyOptionalForValidParameters() {
    Long brandId = 1L;
    Long productId = 2L;
    LocalDateTime applicationDate = LocalDateTime.now();

    PriceModel priceModel = mock(PriceModel.class);

    when(priceModel.getBrand()).thenReturn(mock(BrandModel.class));

    when(priceService.retrievePriceForBrandProductAndDateApplication(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Optional.empty());

    Optional<PriceResponse> priceResponse =
        priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    verify(priceService).retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);
    assertNotNull(priceResponse);
    assertTrue(priceResponse.isEmpty());
  }

  @Test
  @DisplayName("Should return an empty list when input parameters are null.")
  void shouldReturnEmptyListWhenInputParametersAreNull3() {

    Long brandId = 1L;
    Long productId = 2L;
    LocalDateTime applicationDate = LocalDateTime.now();

    PriceModel priceModel = mock(PriceModel.class);

    when(priceModel.getBrand()).thenReturn(mock(BrandModel.class));

    when(priceService.retrievePriceForBrandProductAndDateApplication(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Optional.of(priceModel));

    Optional<PriceResponse> priceResponse =
        priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    verify(priceService).retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);
    assertNotNull(priceResponse);
    assertFalse(priceResponse.isEmpty());
    assertEquals(priceModel.getId(), priceResponse.get().getPriceId());
    assertEquals(priceModel.getPrice(), priceResponse.get().getPrice());
    assertEquals(priceModel.getBrand().getId(), priceResponse.get().getBrandId());
    assertEquals(priceModel.getProductId(), priceResponse.get().getProductId());
    assertEquals(priceModel.getStartDate(), priceResponse.get().getStartDate());
    assertEquals(priceModel.getEndDate(), priceResponse.get().getEndDate());

  }

}
