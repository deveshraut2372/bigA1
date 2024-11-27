package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter @NoArgsConstructor
public class ResultCalReqDto {

    private Integer lineId;

    private String lineName;

    private Integer lineOpenNo;

    private String lineMidNo;

    private Integer lineCloseNo;

    private Long id;

    private String session;

}
