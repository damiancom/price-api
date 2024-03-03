package org.technical.exam.priceapi.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.infrastructure.entity.Brand;
import org.technical.exam.priceapi.infrastructure.entity.Price;

class PriceMapperTest {

  private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

  @Test
  @DisplayName("Should return null when the Price list is null.")
  void shouldReturnNullWhenPriceListIsNull() {
    List<PriceModel> priceModelList = priceMapper.toListModel(null);

    assertNull(priceModelList);
  }

  @Test
  @DisplayName("Should return an empty list when the Price list is empty.")
  void shouldReturnEmptyListWhenPriceListIsEmpty() {
    List<Price> priceList = List.of();

    List<PriceModel> priceModelList = priceMapper.toListModel(priceList);

    assertEquals(0, priceModelList.size());
  }

  @Test
  @DisplayName("Should return a list of PriceModel when the Price list has elements.")
  void shouldReturnListOfPriceModelWhenPriceListHasElements() {
    Brand brand = mock(Brand.class);
    Price price = mock(Price.class);
    price.setBrand(brand);

    when(price.getBrand()).thenReturn(brand);

    List<Price> priceList = List.of(
        mock(Price.class),
        price,
        mock(Price.class)
    );

    List<PriceModel> priceModelList = priceMapper.toListModel(priceList);

    assertEquals(priceList.size(), priceModelList.size());
    assertEquals(priceList.get(0).getId(), priceModelList.get(0).getId());
    assertEquals(priceList.get(0).getPriceList(), priceModelList.get(0).getPriceList());
    assertEquals(priceList.get(0).getPrice(), priceModelList.get(0).getPrice());
    assertEquals(priceList.get(0).getProductId(), priceModelList.get(0).getProductId());
    assertEquals(priceList.get(0).getCurr(), priceModelList.get(0).getCurr());
    assertEquals(priceList.get(0).getPriority(), priceModelList.get(0).getPriority());
    assertEquals(priceList.get(0).getEndDate(), priceModelList.get(0).getEndDate());
    assertEquals(priceList.get(0).getStartDate(), priceModelList.get(0).getStartDate());
    assertEquals(priceList.get(0).getBrand(), priceModelList.get(0).getBrand());
    assertEquals(priceList.get(1).getBrand().getId(), priceModelList.get(1).getBrand().getId());
    assertEquals(priceList.get(1).getBrand().getName(), priceModelList.get(1).getBrand().getName());
  }

}
