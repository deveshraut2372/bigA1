package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class BidReportResDto {
    private Double bidPoint;
    private String value;

    public BidReportResDto() {

    }
}
