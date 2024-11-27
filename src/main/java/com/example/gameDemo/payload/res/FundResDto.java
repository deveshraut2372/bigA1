package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class FundResDto {
    private Double points;
    private String tranStatus;
    private Long id;
    private String tranType;
}
