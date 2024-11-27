package com.example.gameDemo.service;

import com.example.gameDemo.payload.req.RechargeAddReq;

import java.util.List;

public interface RechargeAddService {
    

    Boolean updateRechargeAdd(RechargeAddReq rechargeAddReq);

    List getAllBannerMasterList();

    Boolean createRechargeAdd(List<RechargeAddReq> rechargeAddReq);
}
