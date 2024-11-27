package com.example.gameDemo.service.impl;


import com.example.gameDemo.model.TransctionHistory;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.model.UserTransctionHistory;
import com.example.gameDemo.payload.req.DateReqDto;
import com.example.gameDemo.payload.res.ReportCreditDebitTotal;
import com.example.gameDemo.payload.res.ReportPointWiseResDto;
import com.example.gameDemo.payload.res.TransationResDto;
import com.example.gameDemo.payload.res.UserTranstionResDto;
import com.example.gameDemo.repository.TransctionDao;
import com.example.gameDemo.repository.UserTransctionHistoryDao;
import com.example.gameDemo.service.UserTransctionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserTransctionHistoryServiceImpl implements UserTransctionHistoryService {

    @Autowired
    private UserTransctionHistoryDao userTransctionHistoryDao;

    @Autowired
    private TransctionDao transctionDao;

    @Override
    public List getuserTransctionList(Long id) {
        List<UserTranstionResDto> userTranstionResDtos = new ArrayList<>();
        try {

            List<UserTransctionHistory> userTransctionHistories = userTransctionHistoryDao.getByID(id);

//            System.out.println();

            HashMap<String, UserTranstionResDto> uniqueNumberMap = new HashMap<>();

            for (UserTransctionHistory userTransctionHistory : userTransctionHistories) {
                String uniqueNo = userTransctionHistory.getUniqueNo();
                if (!uniqueNumberMap.containsKey(uniqueNo)) {
                    UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
                    userTranstionResDto.setUniqueNo(uniqueNo);
                    userTranstionResDto.setTranStatus(userTransctionHistory.getTranStatus());
                    userTranstionResDto.setSession(userTransctionHistory.getSession());
                    userTranstionResDto.setLineName(userTransctionHistory.getLineName());
                    userTranstionResDto.setTranType(userTransctionHistory.getTranType());
                    userTranstionResDto.setDateTime(userTransctionHistory.getDateTime());
                    List<TransationResDto> transationResDtos = userTransctionHistoryDao.getValue(uniqueNo);

                    double totalPoint = 0.0;
                    for (TransationResDto transationResDto : transationResDtos) {
                        totalPoint =totalPoint + transationResDto.getPoints();
                    }

                    System.out.println(" total Points ="+totalPoint);
                    userTranstionResDto.setBalance(userTransctionHistory.getBalance());
                    userTranstionResDto.setTotalPoint(totalPoint);
                    userTranstionResDto.setTransationResDtos(transationResDtos);
                    uniqueNumberMap.put(uniqueNo, userTranstionResDto);

                    System.out.println(" Balance =="+userTranstionResDto.getBalance());
                    userTranstionResDtos.add(userTranstionResDto);
                }
            }

        List<TransctionHistory> transctionHistoryList=new ArrayList<>();
            transctionHistoryList=transctionDao.getAllByUserid(id);

            for (TransctionHistory transctionHistory : transctionHistoryList) {
                if(transctionHistory.getTranType().equalsIgnoreCase("Add"))
                {
                    UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
//                    userTranstionResDto.setUniqueNo();
                    userTranstionResDto.setTranStatus(transctionHistory.getTranStatus());
                    userTranstionResDto.setTranType(transctionHistory.getTranType());
                    userTranstionResDto.setDateTime(transctionHistory.getDateAndTime());
                    userTranstionResDto.setBalance(transctionHistory.getPoints().toString());
                    userTranstionResDto.setTotalPoint(transctionHistory.getPoints());
                    userTranstionResDtos.add(userTranstionResDto);
                }
                else if(transctionHistory.getTranType().equalsIgnoreCase("Withdraw"))
                {
                    UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
//                    userTranstionResDto.setUniqueNo();
                    userTranstionResDto.setTranStatus(transctionHistory.getTranStatus());
                    userTranstionResDto.setTranType(transctionHistory.getTranType());
                    userTranstionResDto.setDateTime(transctionHistory.getDateAndTime());
                    userTranstionResDto.setBalance(transctionHistory.getPoints().toString());
                    userTranstionResDto.setTotalPoint(transctionHistory.getPoints());
                    userTranstionResDtos.add(userTranstionResDto);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return userTranstionResDtos;
    }

    @Override
    public List getReportPointWise(DateReqDto dateReqDto) {

        if(dateReqDto.getBidDate()==null)
        {
            dateReqDto.setBidDate(new Date());
        }

        List<ReportCreditDebitTotal> reportCreditDebitTotals = new ArrayList<>();
        ReportCreditDebitTotal reportCreditDebitTotal=new ReportCreditDebitTotal();

        List<TransctionHistory> transctionHistoryList=new ArrayList<>();
        transctionHistoryList=transctionDao.getAllByBidDate(dateReqDto.getBidDate());

        List<ReportPointWiseResDto> reportPointWiseResDtoList=new ArrayList<>();

         Double totalCreditAmount=0.0;
         Double totalDebitAmount=0.0;

        for (TransctionHistory transctionHistory : transctionHistoryList) {
            ReportPointWiseResDto reportPointWiseResDto=new ReportPointWiseResDto();
            String  status=transctionHistory.getTranStatus();
            System.out.println("status =="+status);
            if(status.compareTo("Credit")==0)
            {
                totalCreditAmount=totalCreditAmount+transctionHistory.getPoints();
                reportPointWiseResDto.setCreditPoints(transctionHistory.getPoints());
                reportPointWiseResDto.setTranStatus(transctionHistory.getTranStatus());
            } else if(status.compareTo("Debit")==0){
                totalDebitAmount=totalDebitAmount+transctionHistory.getPoints();
                reportPointWiseResDto.setDebitPoint(transctionHistory.getPoints());
                reportPointWiseResDto.setTranStatus(transctionHistory.getTranStatus());
        }
            reportPointWiseResDto.setUserName(transctionHistory.getUserMaster().getUsername());
            reportPointWiseResDto.setDateTime(transctionHistory.getDateAndTime());
            reportPointWiseResDtoList.add(reportPointWiseResDto);

        }

        reportCreditDebitTotal.setTotalDebitAmount(totalDebitAmount);
        reportCreditDebitTotal.setTotalCreditAmount(totalCreditAmount);
        reportCreditDebitTotal.setReportPointWiseResDtoList(reportPointWiseResDtoList);
        reportCreditDebitTotals.add(reportCreditDebitTotal);
        return reportCreditDebitTotals;
    }

    @Override
    public List getTransction(Long id) {
            List<UserTranstionResDto> userTranstionResDtos = new ArrayList<>();
            try {
//            System.out.println();

                HashMap<String, UserTranstionResDto> uniqueNumberMap = new HashMap<>();

                List<TransctionHistory> transctionHistoryList=new ArrayList<>();
                transctionHistoryList=transctionDao.getAllByUserid(id);

                for (TransctionHistory transctionHistory : transctionHistoryList) {
                    if(transctionHistory.getTranType().equalsIgnoreCase("Add"))
                    {
                        UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
//                    userTranstionResDto.setUniqueNo();
                        userTranstionResDto.setTranStatus(transctionHistory.getTranStatus());
                        userTranstionResDto.setTranType(transctionHistory.getTranType());
                        userTranstionResDto.setDateTime(transctionHistory.getDateAndTime());
                        userTranstionResDto.setBalance(transctionHistory.getBalance());
                        userTranstionResDto.setTotalPoint(transctionHistory.getPoints());
                        userTranstionResDtos.add(userTranstionResDto);
                    }
                    else if(transctionHistory.getTranType().equalsIgnoreCase("Withdraw"))
                    {
                        UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
//                    userTranstionResDto.setUniqueNo();
                        userTranstionResDto.setTranStatus(transctionHistory.getTranStatus());
                        userTranstionResDto.setTranType(transctionHistory.getTranType());
                        userTranstionResDto.setDateTime(transctionHistory.getDateAndTime());
                        userTranstionResDto.setBalance(transctionHistory.getBalance());
                        userTranstionResDto.setTotalPoint(transctionHistory.getPoints());
                        userTranstionResDtos.add(userTranstionResDto);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return userTranstionResDtos;
        }

}
