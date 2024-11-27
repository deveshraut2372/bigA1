package com.example.gameDemo.service;



import com.example.gameDemo.payload.req.*;
import com.example.gameDemo.payload.res.*;

import java.util.List;

public interface BidMasterService {
    BidResDto createBidMaster(List<BidMasterReqDto> bidMasterReqDtoList);

    List getAllBidRequest(Long id);

    Boolean openResultCal(ResultCalReqDto resultCalReqDto);

    List getBidMasterDetails(BidMasterReqDto bidMasterReqDto1);

    List<BidValueCountResDto> getBidValueCount(BidMasterReqDto bidMasterReqDto1);

    TotalWinningResDto getTotalWinningAmount(Integer lineId);

    BarchartResDto getWeeklyListByAgentId(Long id);

    DashBoardCountResDto getTodaysBidCount(Long id);

    List getAllBidMasterList();


    List getdemoList(DemoReq demoReq);

    List getBidHistory();

    List getBusinessDateWise(DateReqDto dateReqDto);

    List getReportDayWise(DateReqDto dateReqDto);

    List getReportPointWise(DateReqDto dateReqDto);

    List getBidReportOpen(BidReportReqDto bidReportReqDto);

    List getBidReportClose(BidReportReqDto bidReportReqDto);

    List getBidHistoryByDate(BidMasterDateReq bidMasterDateReq);
}
