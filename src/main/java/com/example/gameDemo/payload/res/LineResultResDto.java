package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class LineResultResDto {
    private String lineName;
    private Double bidPoint;



    public LineResultResDto() {

    }
}
