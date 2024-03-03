package org.technical.exam.priceapi.domain.port;

import java.time.LocalDateTime;
import java.util.List;
import org.technical.exam.priceapi.domain.model.PriceModel;

public interface PricePersistencePort {

  List<PriceModel> findPriceDetailsByBrandAndProductAndApplicationDate(Long brandId,
                                                                       Long productId,
                                                                       LocalDateTime applicationDate);

}
