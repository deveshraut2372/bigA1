package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class ReportLineWiseResDto {
    private String username;
    private String value;
    private Double bidPoint;
    private Double winPoints;

    public ReportLineWiseResDto() {

    }
}
