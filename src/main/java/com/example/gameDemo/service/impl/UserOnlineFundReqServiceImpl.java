package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.BannerMaster;
import com.example.gameDemo.model.TransctionHistory;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.model.UserOnlineFundReqMaster;
import com.example.gameDemo.payload.req.AddPointsOnlineReqDto;
import com.example.gameDemo.payload.req.AdminAddFundReqDto;
import com.example.gameDemo.payload.req.UserOnlineFundReq;
import com.example.gameDemo.payload.res.UserOnlineFundRes;
import com.example.gameDemo.repository.TransctionDao;
import com.example.gameDemo.repository.UserOnlineFundReqDao;
import com.example.gameDemo.repository.UserRepository;
import com.example.gameDemo.service.UserOnlineFundReqService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserOnlineFundReqServiceImpl implements UserOnlineFundReqService {

    @Autowired
    private UserOnlineFundReqDao userOnlineFundReqDao;

    @Autowired
    private UserRepository userRepository;

  @Autowired
  private TransctionDao transctionDao;



    @Override
    public Boolean createUserOnlineFundReq(UserOnlineFundReq userOnlineFundReq) {
        UserOnlineFundReqMaster userOnlineFundReqMaster=new UserOnlineFundReqMaster();
        BeanUtils.copyProperties(userOnlineFundReq,userOnlineFundReqMaster);
        userOnlineFundReqMaster.setStatus("Pending");
        userOnlineFundReqMaster.setDate(new Date());

        if(userOnlineFundReq.getId()==null)
        {
            return false;
        }else {
            UserMaster userMaster = new UserMaster();
            userMaster = userRepository.findById(userOnlineFundReq.getId()).get();
            userOnlineFundReqMaster.setUserMaster(userMaster);
        }


        try {
            userOnlineFundReqDao.save(userOnlineFundReqMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateUserOnlineFundReq(UserOnlineFundReq userOnlineFundReq) {
        UserOnlineFundReqMaster userOnlineFundReqMaster=new UserOnlineFundReqMaster();
        BeanUtils.copyProperties(userOnlineFundReq,userOnlineFundReqMaster);
        userOnlineFundReqMaster.setStatus(userOnlineFundReq.getStatus());
        userOnlineFundReqMaster.setUserOnlineFundId(userOnlineFundReq.getUserOnlineFundId());
        userOnlineFundReqMaster.setDate(new Date());

        if(userOnlineFundReq.getId()==null)
        {
            return false;
        }else {
            UserMaster userMaster = new UserMaster();
            userMaster = userRepository.findById(userOnlineFundReq.getId()).get();
            userOnlineFundReqMaster.setUserMaster(userMaster);
        }

        try {
            userOnlineFundReqDao.save(userOnlineFundReqMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserOnlineFundRes> getAll() {
        return userOnlineFundReqDao.getAll();
    }

    @Override
    public List<UserOnlineFundRes> getByStatus(String status) {
        return userOnlineFundReqDao.getAllByStatus(status);
    }

    @Override
    public List<UserOnlineFundReqMaster> getAllByStatusAndUserId(String status, Long id) {
        return userOnlineFundReqDao.getAllByStatusAndUserId(status,id);
    }

    @Override
    public Boolean addWallatePointsOnline(AddPointsOnlineReqDto addPointsOnlineReqDto) {

        if(addPointsOnlineReqDto.getPoints()==null)
        {
            throw new NullPointerException(" Please add some Points");
        }

        try
        {
            UserOnlineFundReqMaster userOnlineFundReqMaster=new UserOnlineFundReqMaster();
            userOnlineFundReqMaster=getAllByuserOnlineFundId(addPointsOnlineReqDto.getUserOnlineFundId());
            if(userOnlineFundReqMaster.getStatus().equalsIgnoreCase("Accepted"))
            {
                return true;
            }
            userOnlineFundReqMaster.setStatus("Accepted");
            UserMaster userMaster=new UserMaster();
            userMaster=userRepository.findById(addPointsOnlineReqDto.getId()).get();
            userOnlineFundReqMaster.setUserMaster(userMaster);

            AdminAddFundReqDto adminAddFundReqDto=new AdminAddFundReqDto();

            adminAddFundReqDto.setId(addPointsOnlineReqDto.getId());
            adminAddFundReqDto.setFundType(addPointsOnlineReqDto.getFundType());
            adminAddFundReqDto.setPoints(addPointsOnlineReqDto.getPoints());
            adminAddFundReqDto.setTranType(null);

            fundAdd(adminAddFundReqDto);
            userOnlineFundReqDao.save(userOnlineFundReqMaster);

        return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public UserOnlineFundReqMaster getAllByuserOnlineFundId(Integer userOnlineFundId) {
        return userOnlineFundReqDao.getAllByuserOnlineFundId(userOnlineFundId);
    }


    public Boolean fundAdd(AdminAddFundReqDto adminAddFundReqDto) {
        System.out.println(" adminAddFundReqDto =="+adminAddFundReqDto.toString());
        Boolean flag = false;
        try {
            UserMaster userMaster1 = userRepository.getByUserIdWise(adminAddFundReqDto.getId());
            TransctionHistory transctionHistory = new TransctionHistory();
            transctionHistory.setDateAndTime(new Date());
            transctionHistory.setPoints(adminAddFundReqDto.getPoints());
            transctionHistory.setTranType(adminAddFundReqDto.getFundType());
            Double points =userMaster1.getWalletPoints()+adminAddFundReqDto.getPoints();
            transctionHistory.setBalance(points.toString());
            if(adminAddFundReqDto.getFundType().compareTo("Add")==0){
                transctionHistory.setTranStatus("Credit");
            } else if (adminAddFundReqDto.getFundType().compareTo("Withdraw")==0) {
                transctionHistory.setTranStatus("Debit");
            }
            UserMaster userMaster = new UserMaster();
            userMaster.setId(adminAddFundReqDto.getId());
            transctionHistory.setUserMaster(userMaster);

            Double walletPoint = userMaster1.getWalletPoints();
            walletPoint = walletPoint + adminAddFundReqDto.getPoints();
            userMaster1.setWalletPoints(walletPoint);

            userRepository.save(userMaster1);
            transctionDao.save(transctionHistory);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
