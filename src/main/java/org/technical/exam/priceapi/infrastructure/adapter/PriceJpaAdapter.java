package org.technical.exam.priceapi.infrastructure.adapter;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.domain.port.PricePersistencePort;
import org.technical.exam.priceapi.infrastructure.entity.Price;
import org.technical.exam.priceapi.infrastructure.mapper.PriceMapper;
import org.technical.exam.priceapi.infrastructure.repository.PriceRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceJpaAdapter implements PricePersistencePort {

  final PriceRepository repository;

  @Override
  public List<PriceModel> findPriceDetailsByBrandAndProductAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate) {
    List<Price> priceList =
        repository
            .findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);

    PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);
    return priceMapper.toListModel(priceList);
  }
}
