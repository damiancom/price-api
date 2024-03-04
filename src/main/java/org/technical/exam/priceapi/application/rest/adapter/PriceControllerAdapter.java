package org.technical.exam.priceapi.application.rest.adapter;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.technical.exam.priceapi.application.rest.mapper.PriceMapper;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.domain.port.PriceControllerPort;
import org.technical.exam.priceapi.domain.service.ProductPriceService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceControllerAdapter implements PriceControllerPort {

  final ProductPriceService priceService;

  @Override
  public Optional<PriceResponse> retrievePriceForBrandProductAndDateApplication(Long brandId, Long productId,
                                                                                LocalDateTime applicationDate) {

    Optional<PriceModel> priceModel = priceService.retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate);

    return priceModel.map(Mappers.getMapper(PriceMapper.class)::toResponse);
  }

}
