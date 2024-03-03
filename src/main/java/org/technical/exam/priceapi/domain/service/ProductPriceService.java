package org.technical.exam.priceapi.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.technical.exam.priceapi.domain.model.PriceModel;

public interface ProductPriceService {

  Optional<PriceModel> retrievePriceForBrandProductAndDateApplication
      (Long brandId, Long productId, LocalDateTime applicationDate);

}
