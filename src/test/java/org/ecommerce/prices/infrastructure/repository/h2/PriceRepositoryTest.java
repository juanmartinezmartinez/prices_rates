package org.ecommerce.prices.infrastructure.repository.h2;

import org.ecommerce.prices.infrastructure.repository.h2.model.Prices;
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

class PriceRepositoryTest {

    private PriceRepository sut;
    private H2PriceRespository h2PriceRepository;

    @BeforeEach
    void setup() {
        h2PriceRepository = mock(H2PriceRespository.class);
        sut = new PriceRepository(h2PriceRepository);
    }

    @Test
    void findByProductAndBrandAndDate() {
        Integer product = 35455;
        Integer brand = 1;
        Date date = new Date();

        List<Prices> prices = new ArrayList<>();
        Prices price = new Prices();
        price.setPrice(22.22);
        price.setId(1);
        price.setProductId(1);
        price.setBrandId(1);
        price.setEndDate(new Date());
        price.setStartDate(new Date());
        prices.add(price);

        when(h2PriceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(any(), any(), any(), any()))
                .thenReturn(prices);

        List<org.ecommerce.prices.domain.Prices> pricesDomain = sut.findByProductAndBrandAndDate(product, brand, date);
        assertNotNull(pricesDomain);
        assertEquals(22.22, pricesDomain.get(0).getPrice());
    }
}