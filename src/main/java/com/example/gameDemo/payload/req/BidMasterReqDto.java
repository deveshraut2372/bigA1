package com.example.gameDemo.payload.req;

import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.model.UserMaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter @Setter @NoArgsConstructor
public class    BidMasterReqDto {

    private Integer bidId;
    private Date bidDate;
    private String game;
    private Double bidPoint;
    private String value;
    private String session;
    private String status;
    private Long id;
    private UserMaster userMaster;
    private LineMaster lineMaster;
    private Integer lineId;
    private String gameName;
    private String lineName;
    private String gameRateName;

}
