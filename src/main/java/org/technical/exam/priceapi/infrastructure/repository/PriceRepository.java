package org.technical.exam.priceapi.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.technical.exam.priceapi.infrastructure.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

  /**
   * Retrieves a list of current prices for a specific product from a particular store for a given date.
   *
   * @param brandId   Brand identifier
   * @param productId Product identifier
   * @param applicationDate Price validity date
   * @return A list of current prices for the defined parameters
   */
  @Query("SELECT p "
      + "FROM Price p "
      + "WHERE p.brand.id = :brandId "
      + "AND p.productId = :productId "
      + "AND :applicationDate BETWEEN p.startDate AND p.endDate "
      + "ORDER BY p.priority DESC")
  @Transactional(readOnly = true)
  List<Price> findPriceDetailsByBrandAndProductAndApplicationDate(
      @Param("brandId") Long brandId,
      @Param("productId") Long productId,
      @Param("applicationDate") LocalDateTime applicationDate
  );

}
