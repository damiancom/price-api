package org.technical.exam.priceapi.infrastructure.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.infrastructure.entity.Price;

@Mapper
public interface PriceMapper {

  @Mapping(target = "brand", source = "brand")
  List<PriceModel> toListModel (List<Price> priceList);

}
