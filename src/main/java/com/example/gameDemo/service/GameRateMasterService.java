package com.example.gameDemo.service;



import com.example.gameDemo.payload.req.GameRateMasterReqDto;
import com.example.gameDemo.payload.res.GameRateMasterResDto;
import com.example.gameDemo.payload.res.GameRateUserWiseResDto;

import java.util.List;

public interface GameRateMasterService {
    
    Boolean createGameRateMaster(GameRateMasterReqDto gameRateMasterReqDto);

    Boolean updateGameRateMaster(GameRateMasterReqDto gameRateMasterReqDto);

    List getAllList();

    List getActiveList();



    List getActiveListGameRateUser(Long id);

    Boolean updateUserIdWiseGameRate(Long id, Double gameRate, Integer gameRateMasterId);


    GameRateUserWiseResDto getGameRateUserMasterIdWise(Integer gameRateUserMasterId);

    GameRateMasterResDto getByIdList(Integer gameRateMasterId);
}
