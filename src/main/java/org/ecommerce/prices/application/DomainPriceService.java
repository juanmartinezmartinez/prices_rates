package org.ecommerce.prices.application;

import org.ecommerce.prices.domain.Prices;
import org.ecommerce.prices.domain.Rate;
import org.ecommerce.prices.domain.repository.PriceRepository;
import org.ecommerce.prices.domain.service.PriceService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class DomainPriceService implements PriceService {

    public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private final PriceRepository priceRepository;

    public DomainPriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Rate rateByProductAndBrandAndDates(Integer product, Integer brand, String originalDate) {

        Date date;
        try {
            date = getDate(originalDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<Prices> prices = priceRepository.findByProductAndBrandAndDate(product, brand, date);

        if (prices.isEmpty()) {
            return null;
        }

        Prices price = getPriceWithMaxPriority(prices);
        Rate rate = buildRate(price);
        return rate;
    }

    private Rate buildRate(Prices price) {
        return Rate.builder()
                .price(price.getPrice())
                .productId(Long.valueOf(price.getProductId()))
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .rate(price.getPriceList())
                .build();
    }

    private Prices getPriceWithMaxPriority(List<Prices> prices) {

        Prices priceResult = new Prices();
        for (Prices price: prices) {
            if (priceResult.getPriority() == null || price.getPriority() > priceResult.getPriority()) {
                priceResult = price;
            }
        }
        return priceResult;
    }

    private Date getDate(String date) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return formatter.parse(date);
    }
}
