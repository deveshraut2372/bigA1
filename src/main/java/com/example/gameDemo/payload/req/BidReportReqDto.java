package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter
@ToString
public class BidReportReqDto {

    private Date bidDate;
    private String lineName;
    private String gameRateName;


}
