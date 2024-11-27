package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.FoundMaster;
import com.example.gameDemo.model.TransctionHistory;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.AdminAddFundReqDto;
import com.example.gameDemo.payload.req.DateReqDto;
import com.example.gameDemo.payload.req.FundRequestDto;
import com.example.gameDemo.payload.res.FundResDto;
import com.example.gameDemo.payload.res.TransctionHistoryResDto;
import com.example.gameDemo.repository.FundRepository;
import com.example.gameDemo.repository.TransctionDao;
import com.example.gameDemo.repository.UserRepository;
import com.example.gameDemo.service.FundService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FundServiceimpl implements FundService {
    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransctionDao transctionDao;

    @Override
    public Boolean createFundRequest(FundRequestDto fundRequestDto) {
        FoundMaster foundMaster = new FoundMaster();

        BeanUtils.copyProperties(fundRequestDto, foundMaster);
        foundMaster.setFundPoints(fundRequestDto.getPoints());
        foundMaster.setDateTime(new Date());
        foundMaster.setStatus("Pending");
        UserMaster userMaster = new UserMaster();
        userMaster.setId(fundRequestDto.getId());
        foundMaster.setUserMaster(userMaster);
        try {
            fundRepository.save(foundMaster);
            if (fundRequestDto.getFundType().equalsIgnoreCase("Withdraw")) {
//                Integer no=userRepository.updatePoints1(fundRequestDto.getId(),fundRequestDto.getPoints());

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getPendingRequestList(String status) {

        try {
            List<FoundMaster> foundMasterList = new ArrayList<>();

            foundMasterList = fundRepository.findAllByStatusOrderByDateTime(status);
            return foundMasterList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Boolean updateFundRequest(FundRequestDto fundRequestDto) {

        try {
            if (fundRequestDto.getStatus().equalsIgnoreCase("Approved")) {
                if (fundRequestDto.getFundType().equalsIgnoreCase("Withdraw")) {
                    Integer no = userRepository.updatePoints1(fundRequestDto.getId(), fundRequestDto.getPoints());
                    TransctionHistory transctionHistory = new TransctionHistory();
                    transctionHistory.setDateAndTime(new Date());
                    transctionHistory.setPoints(fundRequestDto.getPoints());
                    transctionHistory.setTranType(fundRequestDto.getFundType());
                    transctionHistory.setTranStatus("Debit");
                    UserMaster userMaster = new UserMaster();
                    userMaster.setId(fundRequestDto.getId());
                    transctionHistory.setUserMaster(userMaster);
                    transctionDao.save(transctionHistory);
                } else {
                    TransctionHistory transctionHistory = new TransctionHistory();
                    transctionHistory.setDateAndTime(new Date());
                    transctionHistory.setPoints(fundRequestDto.getPoints());
                    transctionHistory.setTranType(fundRequestDto.getFundType());
                    transctionHistory.setTranStatus("Credit");
                    UserMaster userMaster = new UserMaster();
                    userMaster=userRepository.getByUserIdWise(fundRequestDto.getId());
                    userMaster.setWalletPoints(userMaster.getWalletPoints()+fundRequestDto.getPoints());
                    userRepository.save(userMaster);
//                    userMaster.setId(fundRequestDto.getId());
                    transctionHistory.setUserMaster(userMaster);
                    transctionDao.save(transctionHistory);

                }
                Integer no = fundRepository.updateAgentFundRequest(fundRequestDto.getStatus(), fundRequestDto.getReason(), fundRequestDto.getFundRequestId());
            } else {
                Integer no = fundRepository.updateAgentFundRequest(fundRequestDto.getStatus(), fundRequestDto.getReason(), fundRequestDto.getFundRequestId());
                if (fundRequestDto.getFundType().equalsIgnoreCase("Withdraw")) {
//                    Integer no4= userRepository.aprRejAgentFundRequestDao(fundRequestDto.getPoints(), fundRequestDto.getId());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
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

    @Override
    public List getAgentTransactionHistoryList(DateReqDto dateReqDto) {
        List<FundResDto> fundResDtoList = transctionDao.getAgentTransactionHistoryList(dateReqDto.getBidDate());
        return fundResDtoList;
    }

    @Override
    public List getAllTranstionList() {
        List<TransctionHistory> transctionHistories = transctionDao.findAllByOrderByDateAndTimeDesc();
        List<TransctionHistoryResDto> transctionHistoryResDtos = new ArrayList<>();
        for (TransctionHistory transctionHistory : transctionHistories) {
            TransctionHistoryResDto transctionHistoryResDto = new TransctionHistoryResDto();
            transctionHistoryResDto.setTransctionId(transctionHistory.getTransctionId());
            transctionHistoryResDto.setPoints(transctionHistory.getPoints());
            transctionHistoryResDto.setUsername(transctionHistory.getUserMaster().getUsername());
            transctionHistoryResDto.setMobileNo(transctionHistory.getUserMaster().getMobileNo());
            transctionHistoryResDto.setTranStatus(transctionHistory.getTranStatus());
            transctionHistoryResDto.setTranType(transctionHistory.getTranType());
            transctionHistoryResDto.setDateAndTime(transctionHistory.getDateAndTime());
            transctionHistoryResDtos.add(transctionHistoryResDto);
        }
        return transctionHistoryResDtos;
    }

    @Override
    public Integer getPendingCount() {
        Integer count = fundRepository.getPendingCount();
        return count;
    }


}


