package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.GameRateMaster;
import com.example.gameDemo.model.GameRateUserWiseMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.GameRateMasterReqDto;
import com.example.gameDemo.payload.res.GameRateMasterResDto;
import com.example.gameDemo.payload.res.GameRateUserWiseResDto;
import com.example.gameDemo.repository.GameRateMasterDao;
import com.example.gameDemo.repository.GameRateUserDao;
import com.example.gameDemo.repository.UserRepository;
import com.example.gameDemo.service.GameRateMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameRateMasterServiceImpl implements GameRateMasterService {

    @Autowired
    private GameRateMasterDao gameRateMasterDao;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRateUserDao gameRateUserDao;

    @Override
    public Boolean createGameRateMaster(GameRateMasterReqDto gameRateMasterReqDto) {
        GameRateMaster gameRateMaster = new GameRateMaster();
//        BeanUtils.copyProperties(gameRateMasterReqDto,gameRateMaster);

        gameRateMaster.setGameName(gameRateMasterReqDto.getGameName());
        gameRateMaster.setGameRate(gameRateMasterReqDto.getGameRate());
        gameRateMaster.setStatus("Active");

//        UserMaster userMaster = new UserMaster();
//        userMaster.setId(gameRateMasterReqDto.getId());
        try {

            gameRateMasterDao.save(gameRateMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean updateGameRateMaster(GameRateMasterReqDto gameRateMasterReqDto) {

        GameRateMaster gameRateMaster = new GameRateMaster();

        gameRateMaster.setGameRateMasterId(gameRateMasterReqDto.getGameRateMasterId());
        gameRateMaster.setGameName(gameRateMasterReqDto.getGameName());
        gameRateMaster.setGameRate(gameRateMasterReqDto.getGameRate());
        gameRateMaster.setStatus(gameRateMasterReqDto.getStatus());
        try {
            gameRateMasterDao.save(gameRateMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getAllList() {
        return (List) gameRateMasterDao.getAllList();
    }

    @Override
    public List getActiveList() {
        List<GameRateMasterResDto> gameRateMasterResDtos = gameRateMasterDao.getActiveList();
        return gameRateMasterResDtos;
//        return gameRateMasterDao.findAllByStatus("Active");
    }

    @Override
    public GameRateMasterResDto getByIdList(Integer gameRateMasterId) {

        GameRateMasterResDto gameRateMasterResDto = new GameRateMasterResDto();
        GameRateMaster gameRateMasters = gameRateMasterDao.getByGameRateMasterIdWise(gameRateMasterId);
        gameRateMasterResDto.setGameRate(gameRateMasters.getGameRate());
        gameRateMasterResDto.setGameName(gameRateMasters.getGameName());
        gameRateMasterResDto.setGameRateMasterId(gameRateMasters.getGameRateMasterId());
        gameRateMasterResDto.setStatus(gameRateMasters.getStatus());
        return gameRateMasterResDto;
    }

    @Override
    public List getActiveListGameRateUser(Long id) {
        List<GameRateUserWiseResDto> gameRateUserWiseResDtos = gameRateUserDao.getActiveListGameRateUser(id);
        return gameRateUserWiseResDtos;
    }


    //    @Override
//    public Double getGameRate(GameNameAgentIdReqDto gameNameAgentIdReqDto) {
//        Double gameRate=gameRateMasterDao.getGameRateByName(gameNameAgentIdReqDto.getGameName(),gameNameAgentIdReqDto.getAgentId());
//        return gameRate;
//    }
//
//    @Override
//    public List getGameRateListByUserId(Long id) {
//
//
//        List list = gameRateMasterDao.getGameRateListByUserId(id);
//        return list;
//    }

    @Override
    public Boolean updateUserIdWiseGameRate(Long id, Double gameRate, Integer gameRateMasterId) {
        Boolean flag = false;
        try {
            GameRateUserWiseMaster gameRateUserWiseMaster = gameRateUserDao.updateUserIdWiseGameRate(id, gameRateMasterId);
            gameRateUserWiseMaster.setGameRate(gameRate);
            gameRateUserDao.save(gameRateUserWiseMaster);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public GameRateUserWiseResDto getGameRateUserMasterIdWise(Integer gameRateUserMasterId) {
        GameRateUserWiseResDto gameRateMasterResDto = gameRateUserDao.getGameRateUserMasterIdWise(gameRateUserMasterId);
        return gameRateMasterResDto;
    }
}
