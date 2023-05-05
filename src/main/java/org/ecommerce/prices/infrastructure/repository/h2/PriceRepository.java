package org.ecommerce.prices.infrastructure.repository.h2;

import org.ecommerce.prices.domain.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PriceRepository implements org.ecommerce.prices.domain.repository.PriceRepository {

    private final H2PriceRespository h2PriceRepository;

    @Autowired
    public PriceRepository(H2PriceRespository h2PriceRepository) {
        this.h2PriceRepository = h2PriceRepository;
    }

    @Override
    public List<Prices> findByProductAndBrandAndDate(Integer product, Integer brand, Date date) {
        List<org.ecommerce.prices.infrastructure.repository.h2.model.Prices> prices
                = h2PriceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(product, brand, date, date);

        return prices.stream().map(price -> buildPrice(price)).toList();
    }

    private Prices buildPrice(org.ecommerce.prices.infrastructure.repository.h2.model.Prices price) {
        return Prices.builder()
                .priceList(price.getPriceList())
                .id(price.getId())
                .brandId(price.getBrandId())
                .currency(price.getCurrency())
                .price(price.getPrice())
                .startDate(price.getStartDate())
                .productId(price.getProductId())
                .endDate(price.getEndDate())
                .priority(price.getPriority())
                .build();
    }
}
