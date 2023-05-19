package com.agl.hiberus.controllers;

import com.agl.hiberus.dto.PricesDto;
import com.agl.hiberus.entities.Prices;
import com.agl.hiberus.exceptions.MissingHeaderInfoException;
import com.agl.hiberus.exceptions.RecordNotFoundException;
import com.agl.hiberus.services.impl.PricesServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricesRestController.class)
public class PricesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricesServiceImpl pricesService;

    static PricesDto priceBetween0614And1231, price0614, price0615, priceBetween0615And1231;

    private static final Long brandId = 1L;
    private static final Long productId = 35455L;

    private static final String url = "/api/brands/{brandId}/products/{productId}";

    @BeforeAll
    static void setup(){
        priceBetween0614And1231 = PricesDto.builder().id(1L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(1).productId(productId).priority(1).price(35.50).currency("EUR").build();

        price0614 = PricesDto.builder().id(2L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, Month.JUNE, 14, 18, 30))
                .priceList(2).productId(productId).priority(1).price(25.45).currency("EUR").build();

        price0615 = PricesDto.builder().id(3L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.JUNE, 15, 11, 0))
                .priceList(3).productId(productId).priority(1).price(30.50).currency("EUR").build();

        priceBetween0615And1231 = PricesDto.builder().id(4L).brandId(brandId)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 16, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(4).productId(productId).priority(1).price(38.95).currency("EUR").build();
    }

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200614T100000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0);

        Prices expectedResult = new ModelMapper().map(priceBetween0614And1231, Prices.class);

        given(pricesService.findPrice(brandId, productId, applicationDate)).willReturn(expectedResult);

        String expectedJsonResult = "{\"id\":1,\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\",\"priceList\":1,\"productId\":35455,\"price\":35.5}";

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(response).isNotNull();
        assertThat(response.getResponse().getContentAsString()).isEqualTo(expectedJsonResult);

        verify(pricesService).findPrice(brandId, productId, applicationDate);
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200614T160000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 16, 0);

        Prices expectedResult = new ModelMapper().map(price0614, Prices.class);

        given(pricesService.findPrice(brandId, productId, applicationDate)).willReturn(expectedResult);

        String expectedJsonResult = "{\"id\":2,\"brandId\":1,\"startDate\":\"2020-06-14T15:00:00\"," +
                "\"endDate\":\"2020-06-14T18:30:00\",\"priceList\":2,\"productId\":35455,\"price\":25.45}";

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(response).isNotNull();
        assertThat(response.getResponse().getContentAsString()).isEqualTo(expectedJsonResult);

        verify(pricesService).findPrice(brandId, productId, applicationDate);
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200614T210000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 21, 0);
        Prices expectedResult = new ModelMapper().map(priceBetween0614And1231, Prices.class);

        given(pricesService.findPrice(brandId, productId, applicationDate)).willReturn(expectedResult);

        String expectedJsonResult = "{\"id\":1,\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\",\"priceList\":1,\"productId\":35455,\"price\":35.5}";

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(response).isNotNull();
        assertThat(response.getResponse().getContentAsString()).isEqualTo(expectedJsonResult);

        verify(pricesService).findPrice(brandId, productId, applicationDate);
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200615T100000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 15, 10, 0);
        Prices expectedResult = new ModelMapper().map(price0615, Prices.class);

        given(pricesService.findPrice(brandId, productId, applicationDate)).willReturn(expectedResult);

        String expectedJsonResult = "{\"id\":3,\"brandId\":1,\"startDate\":\"2020-06-15T00:00:00\"," +
                "\"endDate\":\"2020-06-15T11:00:00\",\"priceList\":3,\"productId\":35455,\"price\":30.5}";

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(response).isNotNull();
        assertThat(response.getResponse().getContentAsString()).isEqualTo(expectedJsonResult);

        verify(pricesService).findPrice(brandId, productId, applicationDate);
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenADate20200616T210000_whenFindByBrandIdProductIdBetweenDates_thenReturnAPriceWithHigherPriority() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 16, 21, 0);
        Prices expectedResult = new ModelMapper().map(priceBetween0615And1231, Prices.class);

        given(pricesService.findPrice(brandId, productId, applicationDate)).willReturn(expectedResult);

        String expectedJsonResult = "{\"id\":4,\"brandId\":1,\"startDate\":\"2020-06-15T16:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\",\"priceList\":4,\"productId\":35455,\"price\":38.95}";

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(response).isNotNull();
        assertThat(response.getResponse().getContentAsString()).isEqualTo(expectedJsonResult);

        verify(pricesService).findPrice(brandId, productId, applicationDate);
    }

    @Test
    public void givenADate20210615T120000_whenFindByBrandIdProductIdBetweenDates_thenReturnANotFoundException() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2021, Month.JUNE, 15, 12, 0);

        given(pricesService.findPrice(brandId, productId, applicationDate)).willThrow(RecordNotFoundException.class);

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(pricesService).findPrice(brandId, productId, applicationDate);
    }

    @Test
    public void givenANullDate_whenFindByBrandIdProductIdBetweenDates_thenReturnAMissingHeaderInfoException() throws Exception {
        given(pricesService.findPrice(brandId, productId, null)).willThrow(MissingHeaderInfoException.class);

        MvcResult response = mockMvc.perform(get(url, brandId, productId)
                        .param("applicationDate", (String) null)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        verify(pricesService, never()).findPrice(brandId, productId, null);
    }

    @Test
    public void givenABrandIdNull_whenFindByBrandIdProductIdBetweenDates_thenReturnAMissingHeaderInfoException() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 15, 12, 0);

        MvcResult response = mockMvc.perform(get(url, null, productId)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(pricesService,never()).findPrice(null, productId, applicationDate);
    }

    @Test
    public void givenAProductIdNull_whenFindByBrandIdProductIdBetweenDates_thenReturnAMissingHeaderInfoException() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 15, 12, 0);

        MvcResult response = mockMvc.perform(get(url, brandId, null)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(pricesService, never()).findPrice(brandId, null, applicationDate);
    }

    @Test
    public void givenABrandIdAndProductIdNull_whenFindByBrandIdProductIdBetweenDates_thenReturnAMissingHeaderInfoException() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 15, 12, 0);

        MvcResult response = mockMvc.perform(get(url, null, null)
                        .param("applicationDate", String.valueOf(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(pricesService,never()).findPrice(null, null, applicationDate);
    }
}
