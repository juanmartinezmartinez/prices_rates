package org.ecommerce.prices.infrastructure.repository.h2;

import org.ecommerce.prices.infrastructure.model.Prices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface H2PriceRespository extends CrudRepository<Prices, Integer> {
    List<Prices> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(Integer productId, Integer brandId, Date startDate, Date endDate);
}
