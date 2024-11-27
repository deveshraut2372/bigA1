package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.GameRateMaster;
import com.example.gameDemo.model.RechargeAddMaster;
import com.example.gameDemo.payload.req.RechargeAddReq;
import com.example.gameDemo.repository.RechargeAddDao;
import com.example.gameDemo.service.RechargeAddService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RechargeAddServiceImpl implements RechargeAddService {
    @Autowired
    private RechargeAddDao rechargeAddDao;


//    @Override
//    public Boolean createRechargeAdd(RechargeAddReq rechargeAddReq) {
//        RechargeAddMaster rechargeAddMaster=new RechargeAddMaster();
////        BeanUtils.copyProperties(gameRateMasterReqDto,gameRateMaster);
//
//        rechargeAddMaster.setNumber(rechargeAddReq.getNumber());
//
////        gameRateMaster.setStatus("Active");
//
////        UserMaster userMaster = new UserMaster();
////        userMaster.setId(gameRateMasterReqDto.getId());
//        try {
//
//            rechargeAddDao.save(rechargeAddMaster);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//
//
//    }


    @Override
    public Boolean updateRechargeAdd(RechargeAddReq rechargeAddReq) {
        RechargeAddMaster rechargeAddMaster = new RechargeAddMaster();


        rechargeAddMaster.setNumber(rechargeAddReq.getNumber());
        rechargeAddMaster.setRechargeAddId(rechargeAddReq.getRechargeAddId());
//        gameRateMaster.setStatus("Active");

//        UserMaster userMaster = new UserMaster();
//        userMaster.setId(gameRateMasterReqDto.getId());
        try {

            rechargeAddDao.save(rechargeAddMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;


        }
    }

    @Override
    public List getAllBannerMasterList() {
        return (List) rechargeAddDao.findAll();
    }

    @Override
    public Boolean createRechargeAdd(List<RechargeAddReq> rechargeAddReq) {
        boolean flag = false;

        for (RechargeAddReq addReq : rechargeAddReq) {
            if(!rechargeAddDao.existsByNumber(addReq.getNumber()))
            {
                RechargeAddMaster newMaster = new RechargeAddMaster();
                newMaster.setNumber(addReq.getNumber());
                try {
                    rechargeAddDao.save(newMaster);
                    flag= true;
                }catch (Exception e)
                {
                    e.printStackTrace();
                    return  false;
                }
            }
        }

//        for (RechargeAddReq req : rechargeAddReq) {
//            boolean numberExists = false;
//            for (RechargeAddMaster master : rechargeAddDao.findAll()) {
//                if (master.getNumber() == req.getNumber()) {
//                    numberExists = true;
//                    break;
//                }
//            }
//            if (!numberExists) {
//                RechargeAddMaster newMaster = new RechargeAddMaster();
//                newMaster.setNumber(req.getNumber());
//                rechargeAddDao.save(newMaster);
//                flag = true;
//            }
//        }

        return flag;
    }

}


