package com.example.gameDemo.service;

import com.example.gameDemo.model.UserOnlineFundReqMaster;
import com.example.gameDemo.payload.req.AddPointsOnlineReqDto;
import com.example.gameDemo.payload.req.UserOnlineFundReq;
import com.example.gameDemo.payload.res.UserOnlineFundRes;

import java.util.List;

public interface UserOnlineFundReqService {
    Boolean createUserOnlineFundReq(UserOnlineFundReq userOnlineFundReq);

    Boolean updateUserOnlineFundReq(UserOnlineFundReq userOnlineFundReq);

    List<UserOnlineFundRes> getAll();

    List<UserOnlineFundRes> getByStatus(String status);

    List<UserOnlineFundReqMaster> getAllByStatusAndUserId(String status, Long id);

    Boolean addWallatePointsOnline(AddPointsOnlineReqDto addPointsOnlineReqDto);

    UserOnlineFundReqMaster getAllByuserOnlineFundId(Integer userOnlineFundId);
}
