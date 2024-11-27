package com.example.gameDemo.service;

import com.example.gameDemo.payload.req.bidNoCopyModelReq;
import com.example.gameDemo.payload.res.BidNoCopyRes;
import com.example.gameDemo.payload.res.GamelogicResDto;
import com.example.gameDemo.payload.res.UserTranstionResDto;

import java.util.List;

public interface BidNoCopyService {
    Boolean copyBidNo(bidNoCopyModelReq bidNoCopyModelReq);

    List<GamelogicResDto> getCopyBidNo(Long userId);
}
