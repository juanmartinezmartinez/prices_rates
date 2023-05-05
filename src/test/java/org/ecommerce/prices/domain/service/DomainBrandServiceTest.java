package org.ecommerce.prices.domain.service;

import org.ecommerce.prices.application.DomainBrandService;
import org.ecommerce.prices.domain.Brands;
import org.ecommerce.prices.domain.repository.BrandsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DomainBrandServiceTest {

    private DomainBrandService sut;
    private BrandsRepository brandsRepository;

    @BeforeEach
    void setup() {
        brandsRepository = mock(BrandsRepository.class);
        sut = new DomainBrandService(brandsRepository);
    }

    @Test
    void getBrandId() {
        List<Brands> brands = new ArrayList<Brands>();
        Brands brand = new Brands();
        brand.setId(1);
        brand.setName("ZARA");
        brands.add(brand);

        when(brandsRepository.findAll()).thenReturn(brands.stream());

        Integer idBrand = sut.getBrandId("ZARA");

        verify(brandsRepository).findAll();
        assertNotNull(idBrand);
        assertEquals(idBrand, brands.get(0).getId());
        assertEquals("ZARA", brands.get(0).getName());
    }
}