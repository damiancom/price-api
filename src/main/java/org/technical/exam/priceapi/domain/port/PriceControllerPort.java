package org.technical.exam.priceapi.domain.port;

import java.time.LocalDateTime;
import java.util.Optional;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;

public interface PriceControllerPort {

  Optional<PriceResponse> retrievePriceForBrandProductAndDateApplication
      (Long brandId, Long productId, LocalDateTime applicationDate);

}
