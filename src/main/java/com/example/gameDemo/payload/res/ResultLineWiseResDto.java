package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter@AllArgsConstructor
public class ResultLineWiseResDto {
    private String lineMidNo;
    private Integer lineCloseNo;
    private Integer lineOpenNo;
    private String lineDate;

    public ResultLineWiseResDto() {

    }
}
