package com.example.travel.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TourFilter {
    private Long priceFrom;

    private Long priceTo;

    private Integer review;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    private String destination;
}
