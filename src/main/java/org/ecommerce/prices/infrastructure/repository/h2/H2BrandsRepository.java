package org.ecommerce.prices.infrastructure.repository.h2;

import org.ecommerce.prices.infrastructure.model.Brands;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2BrandsRepository extends CrudRepository<Brands, Integer> {
}
