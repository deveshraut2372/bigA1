package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class GameLogicReq {
    private String type;
    private String bidNo;
    private String session;
    private Double point;
}
