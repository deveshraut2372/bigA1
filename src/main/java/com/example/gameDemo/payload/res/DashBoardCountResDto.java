package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DashBoardCountResDto
{
    private Long activeUserCnt;
    private Long activeAgentCnt;
    private Long totalBidAmount;
    private Long totalWinAmount;

    private Long todayBidCnt;
}
