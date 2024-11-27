package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class GameRateMasterReqDto {

    private Integer gameRateMasterId;
    private String gameName;
    private Double gameRate;
    private String status;
//    private Long  id;
}
