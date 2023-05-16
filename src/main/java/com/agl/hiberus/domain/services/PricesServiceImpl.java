package com.agl.hiberus.domain.services;

import com.agl.hiberus.application.services.PricesService;
import com.agl.hiberus.domain.entities.Prices;
import com.agl.hiberus.domain.exceptions.MissingHeaderInfoException;
import com.agl.hiberus.domain.exceptions.RecordNotFoundException;
import com.agl.hiberus.domain.repositories.PricesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;

@Service
public class PricesServiceImpl implements PricesService {

    private final PricesRepository pricesRepository;

    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public Prices findPrice(Long brandId, Long productId, LocalDateTime applicationDate) throws RecordNotFoundException, MissingHeaderInfoException {
        if (brandId == null || productId == null || applicationDate == null)
            throw new MissingHeaderInfoException("values has not be null");

        //these two variables are created only to differentiate them when using them as parameters for the query
        LocalDateTime applicationDateForStartDate = applicationDate;
        LocalDateTime applicationDateForEndDate = applicationDate;

        Collection<Prices> priceList = pricesRepository.
                findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId,
                        applicationDateForStartDate, applicationDateForEndDate);

        if(priceList.isEmpty())
            throw new RecordNotFoundException("the price of the product " + productId + " for the brand " + brandId +
                    " was not found on the requested date " + applicationDate);

        return sortedByHighPriority(priceList);
    }

    /**
     * this sort reverse by priority and get the first value
     * @param priceList - list of prices
     * @return Prices object
     */
    private Prices sortedByHighPriority(Collection<Prices> priceList){
        return priceList.stream().max(Comparator.comparing(Prices::getPriority)).get();
    }
}
