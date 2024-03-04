package org.technical.exam.priceapi.application.rest.controller.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.technical.exam.priceapi.application.rest.adapter.PriceControllerAdapter;
import org.technical.exam.priceapi.application.rest.controller.PriceController;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PriceControllerTest {

  @InjectMocks
  private PriceController priceController;

  @Mock
  private PriceControllerAdapter priceControllerAdapter;

  @Test
  @DisplayName("Should return status 404 Not Found when all parameters are null.")
  void shouldReturnStatus404NotFoundWhenAllParametersAreNull() {

    when(priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(null, null, null))
        .thenReturn(Optional.empty());

    var response = priceController.getPrice(null, null, null);

    verify(priceControllerAdapter).retrievePriceForBrandProductAndDateApplication(null, null, null);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @DisplayName("Should return status 404 Not Found when receiving an empty Optional from the adapter.")
  void shouldReturnStatus404NotFoundWhenReceivingEmptyOptionalFromAdapter() {

    Long brandId = 1L;
    Long productId = 2L;
    LocalDateTime applicationDate = LocalDateTime.now();

    when(priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Optional.empty());

    var response = priceController.getPrice(brandId, productId, applicationDate);

    verify(priceControllerAdapter).retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @DisplayName("Should return status 200 OK when receiving all valid parameters and a non-empty PriceResponse from the adapter.")
  void shouldReturnStatus200OKWhenReceivingAllValidParametersAndNonEmptyPriceResponseFromAdapter() {

    Long brandId = 1L;
    Long productId = 2L;
    LocalDateTime applicationDate = LocalDateTime.now();

    Optional<PriceResponse> priceResponse = Optional.of(mock(PriceResponse.class));

    when(priceControllerAdapter.retrievePriceForBrandProductAndDateApplication(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(priceResponse);

    ResponseEntity<PriceResponse> response = priceController.getPrice(brandId, productId, applicationDate);

    verify(priceControllerAdapter).retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(priceResponse.get().getPriceId(), response.getBody().getPriceId());
    assertEquals(priceResponse.get().getPrice(), response.getBody().getPrice());
    assertEquals(priceResponse.get().getBrandId(), response.getBody().getBrandId());
    assertEquals(priceResponse.get().getProductId(), response.getBody().getProductId());
    assertEquals(priceResponse.get().getEndDate(), response.getBody().getEndDate());
    assertEquals(priceResponse.get().getStartDate(), response.getBody().getStartDate());
  }

}
