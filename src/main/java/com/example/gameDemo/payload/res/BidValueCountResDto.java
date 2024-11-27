package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BidValueCountResDto
{
    private String value;
    private Long countValue;
    private Double totalAmount;
    private String session;

    public BidValueCountResDto(String value, Long countValue, Double totalAmount, String session) {
        this.value = value;
        this.countValue = countValue;
        this.totalAmount = totalAmount;
        this.session = session;
    }
}
