package com.agl.hiberus.application.controllers;

import com.agl.hiberus.application.dto.PricesDto;
import com.agl.hiberus.application.services.PricesService;
import com.agl.hiberus.domain.entities.Prices;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class PricesRestController {

    private final PricesService pricesService;

    public PricesRestController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    /**
     * find the price of a brand product for a certain date
     *
     * @param brandId - id of the brand
     * @param productId - id of the product
     * @param applicationDate - date to filter the priceList
     * @return a Prices with higher priority
     */
    @GetMapping("/brands/{brandId}/products/{productId}")
    public ResponseEntity<PricesDto> find(@PathVariable("brandId") Long brandId, @PathVariable("productId") Long productId,
                                         @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate){
            Prices price = pricesService.findPrice(brandId, productId, applicationDate);
            return ResponseEntity.ok(new ModelMapper().map(price, PricesDto.class));
    }
}
