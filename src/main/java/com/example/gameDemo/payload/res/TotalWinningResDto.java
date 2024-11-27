package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TotalWinningResDto {

    private Long totalBidpointAmount;
    private Double totalCountBidpoint;
    private Long totalWinPointsAmount;
    private Double totalCountWinPoints;
    public TotalWinningResDto()
    {

    }
    public TotalWinningResDto(Long totalBidpointAmount, Double totalCountBidpoint) {
        this.totalBidpointAmount = totalBidpointAmount;
        this.totalCountBidpoint = totalCountBidpoint;
    }
    public TotalWinningResDto(Double totalCountWinPoints, Long totalWinPointsAmount) {
        this.totalWinPointsAmount = totalWinPointsAmount;
        this.totalCountWinPoints = totalCountWinPoints;
    }
}
