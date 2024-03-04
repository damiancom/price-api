package org.technical.exam.priceapi.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.infrastructure.entity.Price;
import org.technical.exam.priceapi.infrastructure.repository.PriceRepository;

@SpringBootTest
class PriceJpaAdapterTest {

  @InjectMocks
  private PriceJpaAdapter priceJpaAdapter;

  @Mock
  private PriceRepository priceRepository;


  @Test
  @DisplayName("Should return an empty list when input parameters are null.")
  void shouldReturnEmptyListWhenInputParametersAreNull() {

    when(priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(null, null, null))
        .thenReturn(Collections.emptyList());

    List<PriceModel> priceModelList = priceJpaAdapter.findPriceDetailsByBrandAndProductAndApplicationDate(null, null, null);

    verify(priceRepository).findPriceDetailsByBrandAndProductAndApplicationDate(null, null, null);
    assertNotNull(priceModelList);
    assertTrue(priceModelList.isEmpty());

  }

  @Test
  @DisplayName("Should return an empty list when the repository returns an empty list.")
  void shouldReturnEmptyListWhenRepositoryReturnsEmptyList() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    when(priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class)))
        .thenReturn(Collections.emptyList());

    List<PriceModel> priceModelList = priceJpaAdapter.findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);

    verify(priceRepository).findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class));
    assertNotNull(priceModelList);
    assertTrue(priceModelList.isEmpty());
  }

  @Test
  @DisplayName("Should return null when the repository returns null.")
  void shouldReturnNullWhenRepositoryReturnsNull() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    when(priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class)))
        .thenReturn(null);

    List<PriceModel> priceModelList = priceJpaAdapter.findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);

    verify(priceRepository).findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class));
    assertNull(priceModelList);
  }

  @Test
  @DisplayName("Should return a list of PriceModel with one element when the repository returns a list with one element.")
  void shouldReturnListOfPriceModelWithOneElementWhenRepositoryReturnsListWithOneElement() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    Price price = mock(Price.class);

    when(priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class)))
        .thenReturn(List.of(price));

    List<PriceModel> priceModelList = priceJpaAdapter.findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);

    verify(priceRepository).findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class));
    assertNotNull(priceModelList);
    assertEquals(1, priceModelList.size());
    assertEquals(price.getId(), priceModelList.get(0).getId());
  }

  @Test
  @DisplayName("Should return a list of PriceModel with multiple elements when the repository returns a list with multiple elements.")
  void shouldReturnListOfPriceModelWithMultipleElementsWhenRepositoryReturnsListWithMultipleElements() {
    Long brandId = 1L;
    Long productId = 1L;
    LocalDateTime applicationDate = LocalDateTime.now();

    Price price1 = mock(Price.class);
    Price price2 = mock(Price.class);
    Price price3 = mock(Price.class);

    when(priceRepository.findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class)))
        .thenReturn(List.of(price1, price2, price3));

    List<PriceModel> priceModelList = priceJpaAdapter.findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);

    verify(priceRepository).findPriceDetailsByBrandAndProductAndApplicationDate(any(), any(), any(LocalDateTime.class));
    assertNotNull(priceModelList);
    assertEquals(3, priceModelList.size());
    assertEquals(price1.getId(), priceModelList.get(0).getId());
    assertEquals(price2.getId(), priceModelList.get(1).getId());
    assertEquals(price3.getId(), priceModelList.get(2).getId());
  }

}
