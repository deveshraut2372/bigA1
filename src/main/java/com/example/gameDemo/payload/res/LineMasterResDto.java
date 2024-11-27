package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter@AllArgsConstructor
public class LineMasterResDto {
    private Integer lineId;
    private String lineName;
    private Date lineStartTime;
    private Date lineEndTime;
    private Date lineDate;
    private Integer lineOpenNo;
    private Date lineTime;
    private String lineMidNo;
    private Integer lineCloseNo;
    private String status;
    private Boolean redFlag;
    private Long id;
    private Boolean openStatus;
    private Boolean finalStatus;
    private Boolean dailyStatus;
    private Boolean timeFlag;


    public LineMasterResDto() {

    }
}
