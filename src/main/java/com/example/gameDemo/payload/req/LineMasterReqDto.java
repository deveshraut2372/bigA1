package com.example.gameDemo.payload.req;

import com.example.gameDemo.model.UserMaster;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class LineMasterReqDto {

    private Integer lineId;

    private String lineName;

    private Date lineStartTime;

    private Date lineEndTime;

    private Date lineDate;

    private Integer lineOpenNo;

    private Integer lineMidNo;

    private Integer lineCloseNo;

    private String status;

    private UserMaster userMaster;

    private Long id;
}
