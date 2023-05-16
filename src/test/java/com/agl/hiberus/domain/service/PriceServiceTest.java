package com.agl.hiberus.domain.service;

import com.agl.hiberus.domain.entities.Prices;
import com.agl.hiberus.domain.exceptions.MissingHeaderInfoException;
import com.agl.hiberus.domain.exceptions.RecordNotFoundException;
import com.agl.hiberus.domain.repositories.PricesRepository;
import com.agl.hiberus.domain.services.PricesServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    @InjectMocks
    PricesServiceImpl pricesService;
    @Mock
    PricesRepository pricesRepository;

    static Prices priceBetween0614And1231, price0614, price0615, priceBetween0615And1231;

    private static final Long productId = 35455L;
    private static final Long brandId = 1L;

    @BeforeAll
    static void setup(){
        priceBetween0614And1231 = Prices.builder().id(1L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(1).productId(productId).priority(0).price(35.50).currency("EUR").build();

        price0614 = Prices.builder().id(2L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, Month.JUNE, 14, 18, 30))
                .priceList(2).productId(productId).priority(1).price(25.45).currency("EUR").build();

        price0615 = Prices.builder().id(3L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.JUNE, 15, 11, 0))
                .priceList(3).productId(productId).priority(1).price(30.50).currency("EUR").build();

        priceBetween0615And1231 = Prices.builder().id(4L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 16, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(4).productId(productId).priority(1).price(38.95).currency("EUR").build();
    }

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200614T100000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0);
        Collection<Prices> expectedList = Collections.singletonList(priceBetween0614And1231);

        when(this.pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate))
                .thenReturn(expectedList);

        Prices response = this.pricesService.findPrice(brandId, productId, applicationDate);

        assertThat(response).isNotNull().isEqualTo(priceBetween0614And1231);

        verify(this.pricesRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate);
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200614T160000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 16, 0);
        Collection<Prices> expectedList = Arrays.asList(priceBetween0614And1231, price0614);

        when(this.pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate))
                .thenReturn(expectedList);

        Prices response = this.pricesService.findPrice(brandId, productId, applicationDate);

        assertThat(response).isNotNull().isEqualTo(price0614);

        verify(this.pricesRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate);
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200614T210000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 21, 0);
        Collection<Prices> expectedList = Collections.singletonList(priceBetween0614And1231);

        when(this.pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate))
                .thenReturn(expectedList);

        Prices response = this.pricesService.findPrice(brandId, productId, applicationDate);

        assertThat(response).isNotNull().isEqualTo(priceBetween0614And1231);

        verify(this.pricesRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate);
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200615T100000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 15, 10, 0);
        Collection<Prices> expectedList = Arrays.asList(priceBetween0614And1231, price0615);

        when(this.pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate))
                .thenReturn(expectedList);

        Prices response = this.pricesService.findPrice(brandId, productId, applicationDate);

        assertThat(response).isNotNull().isEqualTo(price0615);

        verify(this.pricesRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate);
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200616T210000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 16, 21, 0);
        Collection<Prices> expectedList = Arrays.asList(priceBetween0614And1231, priceBetween0615And1231);

        when(this.pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate))
                .thenReturn(expectedList);

        Prices response = this.pricesService.findPrice(brandId, productId, applicationDate);

        assertThat(response).isNotNull().isEqualTo(priceBetween0615And1231);

        verify(this.pricesRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate);
    }

    /**
     * Test 6: petición a las 21:00 del día 13 del producto 35455 para la brand 1 (ZARA)
     * Deberia arrojar un NotFoundException ya que no hay precio para ese dia
     */
    @Test
    public void givenADate20200613T210000_whenFindByBrandIdProductIdBetweenDates_thenReturnANotFoundException() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 13, 21, 0);

        when(this.pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate))
                .thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> this.pricesService.findPrice(brandId, productId, applicationDate))
                .isInstanceOf(RecordNotFoundException.class);

        verify(this.pricesRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,
                productId, applicationDate, applicationDate);
    }

    /**
     * Test 7: petición sin datos Deberia arrojar un MissingHeaderInfoException
     */
    @Test
    public void findNullValuesThrowException() {
        assertThatThrownBy(() -> this.pricesService.findPrice(null, null, null))
                .isInstanceOf(MissingHeaderInfoException.class);

        verify(this.pricesRepository, never()).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(),
                any(), any(), any());
    }
}
