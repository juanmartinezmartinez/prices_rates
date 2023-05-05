package org.ecommerce.prices.domain.service;

import org.ecommerce.prices.domain.Prices;
import org.ecommerce.prices.domain.Rate;
import org.ecommerce.prices.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DomainPriceServiceTest {

    private DomainPriceService sut;
    private PriceRepository priceRepository;

    @BeforeEach
    void setup() {
        priceRepository = mock(PriceRepository.class);
        sut = new DomainPriceService(priceRepository);
    }

    @Test
    void rateByProductAndBrandAndDates() {
        Integer product = 35455;
        Integer brand = 1; // ZARA
        String date = "2020-06-14 10:00:00";

        List<Prices> prices = new ArrayList<>();
        Prices price = new Prices();
        price.setPrice(22.22);
        price.setId(1);
        price.setProductId(1);
        price.setBrandId(1);
        price.setEndDate(new Date());
        price.setStartDate(new Date());
        prices.add(price);

        when(priceRepository.findByProductAndBrandAndDate(any(), any(), any())).thenReturn(prices);

        Rate rate = sut.rateByProductAndBrandAndDates(product, brand, date);

        assertNotNull(rate);
        assertEquals(22.22, rate.getPrice());
    }
}