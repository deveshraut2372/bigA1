package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BidResDto {

    private Integer responseCode;

    private Double walletPoints;

    private String msg;
}
