package com.example.gameDemo.payload.res;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter@Setter
public class BidHistoryResultContainer {
    List<LineResultResDto> lineResultResDtoList=new ArrayList<>();
    private LineHistoryTotalPointResDto lineHistoryTotalPointResDto;
}
