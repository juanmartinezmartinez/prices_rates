package org.ecommerce.prices.infrastructure.api.rest;

import org.ecommerce.prices.domain.service.BrandService;
import org.ecommerce.prices.domain.service.PriceService;
import org.ecommerce.prices.infrastructure.api.rest.request.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class RatesApiDelegateImpl implements RatesApiDelegate {

    public static final String DATE_FORMAT = "yyyy-MM-dd-hh:mm:ss";
    private final BrandService brandService;
    private final PriceService priceService;

    @Autowired
    public RatesApiDelegateImpl(BrandService brandService, PriceService priceService) {
        this.brandService = brandService;
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<Rate> findRates(String date, String productId, String brand) {
        Integer brandId = brandService.getBrandId(brand);
        org.ecommerce.prices.domain.Rate rate = priceService.rateByProductAndBrandAndDates(Integer.valueOf(productId), brandId, date);

        if (rate == null) {
            throw new EntityNotFoundException();
        }

        rate.setBrand(brand);

        return ResponseEntity.ok(domainToVO(rate));
    }

    private Rate domainToVO(org.ecommerce.prices.domain.Rate rate) {
        Rate rateVO = new Rate();
        rateVO.setPrice(rate.getPrice());
        rateVO.setBrand(rate.getBrand());
        rateVO.setProductId(rate.getProductId());
        rateVO.setStartDate(formatDate(rate.getStartDate()));
        rateVO.setEndDate(formatDate(rate.getEndDate()));
        rateVO.setRate(rate.getRate());
        return rateVO;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return formatter.format(date);
    }
}
