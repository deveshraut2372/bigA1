package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@AllArgsConstructor
public class ReportCreditDebitTotal {
    private Double totalCreditAmount;
    private Double totalDebitAmount;
    List<ReportPointWiseResDto> reportPointWiseResDtoList;


    public ReportCreditDebitTotal() {

    }
}
