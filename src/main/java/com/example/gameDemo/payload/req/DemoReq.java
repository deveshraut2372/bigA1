package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DemoReq {
    private Integer lineId;
    private String gameRateName;
    private String value;
    private String session;
}
