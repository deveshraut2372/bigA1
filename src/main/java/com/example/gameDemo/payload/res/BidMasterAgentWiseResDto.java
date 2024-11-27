package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class BidMasterAgentWiseResDto {


    private String fullName;
    private String mobNo;
    private Double bidPoint;
    private Date bidDate;
    private Double walletPoints;
    private String value;
    private String session;

    public BidMasterAgentWiseResDto(String fullName, String mobNo, Double bidPoint, Date bidDate, Double walletPoints, String value, String session) {
        this.fullName = fullName;
        this.mobNo = mobNo;
        this.bidPoint = bidPoint;
        this.bidDate = bidDate;
        this.walletPoints = walletPoints;
        this.value = value;
        this.session = session;
    }
}
