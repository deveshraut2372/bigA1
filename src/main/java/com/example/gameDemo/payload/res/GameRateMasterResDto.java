package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class GameRateMasterResDto {

    private Integer gameRateMasterId;
    private String gameName;
    private Double gameRate;
    private String status;



    public GameRateMasterResDto() {

    }
}
