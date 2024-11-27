package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class LineHistoryTotalPointResDto {
    private Double totalBidPoint;
    List<LineResultResDto> lineResultResDtoList;
}
