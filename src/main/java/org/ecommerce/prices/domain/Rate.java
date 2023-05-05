package org.ecommerce.prices.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    private Long productId;
    private String brand;
    private Double price;
    private Date startDate;
    private Date endDate;
    private Integer rate;
}
