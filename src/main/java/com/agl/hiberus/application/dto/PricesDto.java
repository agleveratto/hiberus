package com.agl.hiberus.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PricesDto implements Serializable {

    private Long id;
    private Long brandId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    @JsonIgnore
    private Integer priority;
    private Double price;
    @JsonIgnore
    private String currency;

    public PricesDto() {
    }

    public PricesDto(Long id, Long brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList,
                     Long productId, Integer priority, Double price, String currency) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public static class PricesDtoBuilder {
        private Long id;
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer priceList;
        private Long productId;
        private Integer priority;
        private Double price;
        private String currency;

        public PricesDtoBuilder() {
        }

        public PricesDtoBuilder id(Long id){
            this.id = id;
            return this;
        }
        public PricesDtoBuilder brandId(Long brandId){
            this.brandId = brandId;
            return this;
        }
        public PricesDtoBuilder startDate(LocalDateTime startDate){
            this.startDate = startDate;
            return this;
        }
        public PricesDtoBuilder endDate(LocalDateTime endDate){
            this.endDate = endDate;
            return this;
        }
        public PricesDtoBuilder priceList(Integer priceList){
            this.priceList = priceList;
            return this;
        }
        public PricesDtoBuilder productId(Long productId){
            this.productId = productId;
            return this;
        }
        public PricesDtoBuilder priority(Integer priority){
            this.priority = priority;
            return this;
        }
        public PricesDtoBuilder price(Double price){
            this.price = price;
            return this;
        }
        public PricesDtoBuilder currency(String currency){
            this.currency = currency;
            return this;
        }

        public PricesDto build(){
            return new PricesDto(id, brandId, startDate, endDate, priceList, productId, priority, price, currency);
        }
    }

    public static PricesDtoBuilder builder(){
        return new PricesDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricesDto pricesDto = (PricesDto) o;
        return Objects.equals(id, pricesDto.id) && Objects.equals(brandId, pricesDto.brandId) &&
                Objects.equals(startDate, pricesDto.startDate) && Objects.equals(endDate, pricesDto.endDate) &&
                Objects.equals(priceList, pricesDto.priceList) && Objects.equals(productId, pricesDto.productId) &&
                Objects.equals(priority, pricesDto.priority) && Objects.equals(price, pricesDto.price) &&
                Objects.equals(currency, pricesDto.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, priceList, productId, priority, price, currency);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}