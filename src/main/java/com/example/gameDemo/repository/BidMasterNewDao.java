package com.example.gameDemo.repository;



import com.example.gameDemo.payload.req.BidMasterReqDto;

import java.util.List;

public interface BidMasterNewDao {

    List getBidMasterDetails(BidMasterReqDto bidMasterReqDto);
}
