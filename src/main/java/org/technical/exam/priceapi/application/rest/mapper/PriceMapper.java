package org.technical.exam.priceapi.application.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;
import org.technical.exam.priceapi.domain.model.PriceModel;

@Mapper
public interface PriceMapper {

  @Mapping(target = "brandId", source = "brand.id")
  @Mapping(target = "priceId", source = "id")
  PriceResponse toResponse(PriceModel priceModel);

}
