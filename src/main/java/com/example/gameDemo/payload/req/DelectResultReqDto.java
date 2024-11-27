package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter@Setter
public class DelectResultReqDto {
    private Integer lineId;
    private Date lineDate;
    private Date lineTime;
    private String lineMidNo;
    private Integer lineCloseNo;
    private String lineName;
    private String session;

}
