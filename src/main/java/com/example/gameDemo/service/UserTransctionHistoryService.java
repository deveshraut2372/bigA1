package com.example.gameDemo.service;

import com.example.gameDemo.payload.req.DateReqDto;

import java.util.List;

public interface UserTransctionHistoryService {
    List getuserTransctionList(Long id);

    List getReportPointWise(DateReqDto dateReqDto);

    List getTransction(Long id);
}
