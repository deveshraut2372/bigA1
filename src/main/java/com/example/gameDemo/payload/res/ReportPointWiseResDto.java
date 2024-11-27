package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter@AllArgsConstructor
public class ReportPointWiseResDto {
    private Date dateTime;
    private String userName;
    private String tranStatus;
private Double creditPoints;
private Double debitPoint;

    public ReportPointWiseResDto() {

    }
}
