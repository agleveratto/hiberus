package com.agl.hiberus.services.impl;

import com.agl.hiberus.services.PricesService;
import com.agl.hiberus.entities.Prices;
import com.agl.hiberus.exceptions.MissingHeaderInfoException;
import com.agl.hiberus.exceptions.RecordNotFoundException;
import com.agl.hiberus.repositories.PricesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PricesServiceImpl implements PricesService {

    private final PricesRepository pricesRepository;

    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public Prices findPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        if (brandId == null || productId == null || applicationDate == null)
            throw new MissingHeaderInfoException("values has not be null");

        //these two variables are created only to differentiate them when using them as parameters for the query
        LocalDateTime applicationDateForStartDate = applicationDate;
        LocalDateTime applicationDateForEndDate = applicationDate;

        Optional<Prices> price = pricesRepository.
                findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId,
                        productId, applicationDateForStartDate, applicationDateForEndDate);

        return price
                .orElseThrow(() ->
                        new RecordNotFoundException(
                String.format("the price of the product {%s} for the brand {%s} was not found on the requested date {%s}",
                        productId, brandId, applicationDate)));
    }
}
