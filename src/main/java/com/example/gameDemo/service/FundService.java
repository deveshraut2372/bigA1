package com.example.gameDemo.service;

import com.example.gameDemo.payload.req.AdminAddFundReqDto;
import com.example.gameDemo.payload.req.DateReqDto;
import com.example.gameDemo.payload.req.FundRequestDto;

import java.util.List;

public interface FundService {
    Boolean createFundRequest(FundRequestDto fundRequestDto);

    List getPendingRequestList(String status);

    Boolean updateFundRequest(FundRequestDto fundRequestDto);

    


    Boolean fundAdd(AdminAddFundReqDto adminAddFundReqDto);

    List getAgentTransactionHistoryList(DateReqDto dateReqDto);

    List getAllTranstionList();

    Integer getPendingCount();
}
