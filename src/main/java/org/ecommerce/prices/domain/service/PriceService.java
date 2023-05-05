package org.ecommerce.prices.domain.service;


import org.ecommerce.prices.domain.Rate;

public interface PriceService {

    Rate rateByProductAndBrandAndDates(Integer product, Integer brand, String date);
}
