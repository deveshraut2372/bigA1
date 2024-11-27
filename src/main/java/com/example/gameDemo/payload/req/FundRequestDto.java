package com.example.gameDemo.payload.req;

import com.example.gameDemo.model.UserMaster;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter@Setter
public class FundRequestDto {
    private Integer fundRequestId;
    private Double points;
    private Date dateTime;
    private String fundType;
    private String status;
    private UserMaster userMaster;
    private Long id;
    private String reason;
    private String fromDate;
    private String toDate;
    private String tranType;
    private String paymentType;
}
