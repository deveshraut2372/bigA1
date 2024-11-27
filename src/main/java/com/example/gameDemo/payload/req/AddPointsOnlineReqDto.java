package com.example.gameDemo.payload.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPointsOnlineReqDto {

    private Long id;
    private Integer userOnlineFundId;
    private Double points;
    private String fundType;
//    private String tranType;

}
