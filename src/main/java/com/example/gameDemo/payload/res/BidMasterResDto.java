package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter@Setter@AllArgsConstructor
public class BidMasterResDto {
    private Integer bidId;
    private Date bidDate;
    private String game;
    private Double bidPoint;
    private String value;
    private String session;
    private String status;
    private Long id;
    private Integer lineId;

}
