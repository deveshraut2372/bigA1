package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class LineHistoryResDto {
    private Integer lineId;
    private String lineName;
    private Date lineDate;
    private String lineMidNo;
    private Integer lineCloseNo;
    private Integer lineOpenNo;

    public LineHistoryResDto() {

    }
}
