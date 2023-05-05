package org.ecommerce.prices.infrastructure.repository.h2;

import org.ecommerce.prices.infrastructure.model.Brands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BrandsRepositoryTest {

    private BrandsRepository sut;
    private H2BrandsRepository h2BrandsRepository;

    @BeforeEach
    void setup() {
        h2BrandsRepository = mock(H2BrandsRepository.class);
        sut = new BrandsRepository(h2BrandsRepository);
    }

    @Test
    void findAll() {
        List<Brands> brands = new ArrayList<Brands>();
        Brands brand = new Brands();
        brand.setId(1);
        brand.setName("ZARA");
        brands.add(brand);

        when(h2BrandsRepository.findAll()).thenReturn(brands);

        Stream<org.ecommerce.prices.domain.Brands> brandsDomain = sut.findAll();

        assertNotNull(brandsDomain);
        assertEquals("ZARA", brandsDomain.toList().get(0).getName());
    }
}