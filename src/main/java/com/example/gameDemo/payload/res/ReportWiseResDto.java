package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter@AllArgsConstructor
public class ReportWiseResDto {
    private Double totalBidPoint;
    List<ReportResDto>  reportResDtos;


    public ReportWiseResDto() {

    }
}
