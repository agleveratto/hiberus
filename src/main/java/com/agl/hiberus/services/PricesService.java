package com.agl.hiberus.services;

import com.agl.hiberus.entities.Prices;
import com.agl.hiberus.exceptions.MissingHeaderInfoException;
import com.agl.hiberus.exceptions.RecordNotFoundException;

import java.time.LocalDateTime;

public interface PricesService {
    /**
     * find the price of a brand product for a certain date
     *
     * @param brandId - id of the brand
     * @param productId - id of the product
     * @param applicationDate - date to filter the priceList
     * @return a Prices with higher priority
     * @throws MissingHeaderInfoException - mandatory values
     * @throws RecordNotFoundException - there isn't price for that date
     */
    Prices findPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}