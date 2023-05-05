package org.ecommerce.prices.domain.repository;


import org.ecommerce.prices.domain.Brands;

import java.util.stream.Stream;

public interface BrandsRepository {

    Stream<Brands> findAll();
}
