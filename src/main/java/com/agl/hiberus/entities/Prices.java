package com.agl.hiberus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Prices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND_ID", nullable = false)
    private Long brandId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    @JsonIgnore
    private Integer priority;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "CURRENCY", nullable = false)
    @JsonIgnore
    private String currency;

    public Prices() {
    }

    public Prices(Long id, Long brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList,
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

    public static class PricesBuilder {
        private Long id;
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer priceList;
        private Long productId;
        private Integer priority;
        private Double price;
        private String currency;

        public PricesBuilder() {
        }

        public PricesBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PricesBuilder brandId(Long brandId) {
            this.brandId = brandId;
            return this;
        }

        public PricesBuilder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public PricesBuilder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public PricesBuilder priceList(Integer priceList) {
            this.priceList = priceList;
            return this;
        }

        public PricesBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public PricesBuilder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public PricesBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public PricesBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Prices build() {
            return new Prices(id, brandId, startDate, endDate, priceList, productId, priority, price, currency);
        }
    }

    public static PricesBuilder builder() {
        return new PricesBuilder();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prices prices = (Prices) o;
        return Objects.equals(id, prices.id) && Objects.equals(brandId, prices.brandId) &&
                Objects.equals(startDate, prices.startDate) && Objects.equals(endDate, prices.endDate) &&
                Objects.equals(priceList, prices.priceList) && Objects.equals(productId, prices.productId) &&
                Objects.equals(priority, prices.priority) && Objects.equals(price, prices.price) &&
                Objects.equals(currency, prices.currency);
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