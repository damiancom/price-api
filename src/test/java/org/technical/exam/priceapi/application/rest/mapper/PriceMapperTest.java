package org.technical.exam.priceapi.application.rest.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;
import org.technical.exam.priceapi.domain.model.BrandModel;
import org.technical.exam.priceapi.domain.model.PriceModel;

class PriceMapperTest {

  private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

  @Test
  @DisplayName("Should return null when the PriceModel is null.")
  void shouldReturnNullWhenPriceModelIsNull() {
    PriceResponse priceResponse = priceMapper.toResponse(null);

    assertNull(priceResponse);
  }

  @Test
  @DisplayName("Should return a PriceResponse when given a PriceModel.")
  void shouldReturnPriceResponseWhenGivenPriceModel() {
    BrandModel brandModel = mock(BrandModel.class);
    PriceModel priceModel = mock(PriceModel.class);

    when(priceModel.getBrand()).thenReturn(brandModel);

    PriceResponse priceResponse = priceMapper.toResponse(priceModel);

    assertEquals(priceModel.getId(), priceResponse.getPriceId());
    assertEquals(priceModel.getPrice(), priceResponse.getPrice());
    assertEquals(priceModel.getProductId(), priceResponse.getProductId());
    assertEquals(priceModel.getEndDate(), priceResponse.getEndDate());
    assertEquals(priceModel.getStartDate(), priceResponse.getStartDate());
    assertEquals(priceModel.getBrand().getId(), priceResponse.getBrandId());
  }

  @DisplayName("Should return PriceResponse with null brandId when provided PriceModel has BrandModel with null id.")
  @Test
  void shouldReturnPriceResponseWithNullBrandIdWhenProvidedPriceModelHasBrandModelWithNullId() {
    BrandModel brandModel = mock(BrandModel.class);
    PriceModel priceModel = mock(PriceModel.class);

    when(priceModel.getBrand()).thenReturn(brandModel);
    when(priceModel.getBrand().getId()).thenReturn(null);

    PriceResponse priceResponse = priceMapper.toResponse(priceModel);

    assertEquals(priceModel.getId(), priceResponse.getPriceId());
    assertEquals(priceModel.getPrice(), priceResponse.getPrice());
    assertEquals(priceModel.getProductId(), priceResponse.getProductId());
    assertEquals(priceModel.getEndDate(), priceResponse.getEndDate());
    assertEquals(priceModel.getStartDate(), priceResponse.getStartDate());
    assertNull(priceResponse.getBrandId());
  }

  @Test
  @DisplayName("Should return PriceResponse with null brandId when provided PriceModel has null BrandModel.")
  void shouldReturnPriceResponseWithNullBrandIdWhenProvidedPriceModelHasNullBrandModel() {
    PriceModel priceModel = mock(PriceModel.class);

    when(priceModel.getBrand()).thenReturn(null);

    PriceResponse priceResponse = priceMapper.toResponse(priceModel);

    assertEquals(priceModel.getId(), priceResponse.getPriceId());
    assertEquals(priceModel.getPrice(), priceResponse.getPrice());
    assertEquals(priceModel.getProductId(), priceResponse.getProductId());
    assertEquals(priceModel.getEndDate(), priceResponse.getEndDate());
    assertEquals(priceModel.getStartDate(), priceResponse.getStartDate());
    assertNull(priceResponse.getBrandId());
  }

}
