package org.ecommerce.prices.domain;

import lombok.*;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prices {

    private Integer id;
    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private Double price;
    private String currency;
}
