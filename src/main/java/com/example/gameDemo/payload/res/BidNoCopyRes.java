package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidNoCopyRes {

    private String tranStatus;
    private String tranType;
    private Long userId;
    private String lineName;
    private String session;
    private String uniqueNo;
    List<TransationResDto> transationResDtos;


}
