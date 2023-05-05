package org.ecommerce.prices.infrastructure.repository.h2;

import org.ecommerce.prices.domain.Brands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class BrandsRepository implements org.ecommerce.prices.domain.repository.BrandsRepository {

    private final H2BrandsRepository h2BrandsRepository;

    @Autowired
    public BrandsRepository(H2BrandsRepository h2BrandsRepository) {
        this.h2BrandsRepository = h2BrandsRepository;
    }

    @Override
    public Stream<Brands> findAll() {
        Iterable<org.ecommerce.prices.infrastructure.model.Brands> brands = h2BrandsRepository.findAll();
        return StreamSupport.stream(brands.spliterator(), false).map(brand -> buildBrand(brand));
    }

    private Brands buildBrand(org.ecommerce.prices.infrastructure.model.Brands brand) {
        return Brands.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
