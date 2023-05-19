package com.agl.hiberus.repositories;

import com.agl.hiberus.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

    /**
     * find the price of a brand product for a certain date.
     *
     * Maybe not the best solution as you get two duplicate parameters,
     * but I preferred to correct the @query with this way and not
     * replace JPA with JDBC as it was not in my plans to use it.
     *
     * @param brandId - id of the brand
     * @param productId - id of the product
     * @param applicationDateForStartDate - date to filter the priceList and used for compare startDate value
     * @param applicationDateForEndDate - date to filter the priceList and user for compare endDate value
     * @return Prices with higher priority
     */
    Optional<Prices> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Long brandId,
                                                                                                                          Long productId,
                                                                                                                          LocalDateTime applicationDateForStartDate,
                                                                                                                          LocalDateTime applicationDateForEndDate);

}
