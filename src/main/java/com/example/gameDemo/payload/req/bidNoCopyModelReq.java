package com.example.gameDemo.payload.req;

import com.example.gameDemo.payload.res.TransationResDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class bidNoCopyModelReq {

    private String tranStatus;
    private String tranType;
    private Long userId;
    private String lineName;
    private String session;
    private String uniqueNo;
    List<TransationResDto> transationResDtos;
}
