package org.ecommerce.prices.domain.repository;

import org.ecommerce.prices.domain.Prices;

import java.util.Date;
import java.util.List;

public interface PriceRepository {
    List<Prices> findByProductAndBrandAndDate(Integer product, Integer brand, Date date);
}
