package com.example.gameDemo.payload.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter@Setter
public class AdminAddFundReqDto {
    private Double points;
    private String fundType;
    private Long id;
    private String tranType;
}
