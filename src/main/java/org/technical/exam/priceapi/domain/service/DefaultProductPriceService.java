package org.technical.exam.priceapi.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.technical.exam.priceapi.domain.model.PriceModel;
import org.technical.exam.priceapi.domain.port.PricePersistencePort;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultProductPriceService implements ProductPriceService {

  final PricePersistencePort pricePersistence;

  public Optional<PriceModel> retrievePriceForBrandProductAndDateApplication(Long brandId, Long productId, LocalDateTime applicationDate) {
    return pricePersistence
        .findPriceDetailsByBrandAndProductAndApplicationDate(brandId, productId, applicationDate)
        .stream().findFirst();
  }

}
