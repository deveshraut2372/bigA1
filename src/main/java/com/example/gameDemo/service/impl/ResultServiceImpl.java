package com.example.gameDemo.service.impl;

import com.example.gameDemo.configuration.FirebaseCloudMessaging;
import com.example.gameDemo.model.*;
import com.example.gameDemo.payload.req.DelectResultReqDto;
import com.example.gameDemo.payload.req.ResultCalReqDto;
import com.example.gameDemo.payload.req.UserTokenReqDto;
import com.example.gameDemo.payload.res.ResultUserResDto;
import com.example.gameDemo.repository.*;
import com.example.gameDemo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
 private    LineMasterDao lineMasterDao;

    @Autowired
    private NewLineMasterDao newLineMasterDao;

    @Autowired
    private BidMasterDao bidMasterDao;

    @Autowired
    private UserRepository userMasterDao;

    @Autowired
    private WiningHistoryDao winingHistoryDao;
    @Autowired
    private GameRateMasterDao gameRateMasterDao;

    @Autowired
    private UserTransctionHistoryDao userTransctionHistoryDao;

    @Autowired
    private GameRateUserDao gameRateUserDao;

    @Autowired
    private TransctionDao transctionHistoryDao;

    @Override
    public Boolean openResultCal(ResultCalReqDto resultCalReqDto) {
        Boolean res=false;
        try{
            Integer no=lineMasterDao.updateResultDetails(resultCalReqDto.getLineId(),resultCalReqDto.getLineOpenNo(),resultCalReqDto.getLineMidNo(),resultCalReqDto.getLineCloseNo());
            NewLineMaster newLineMaster=newLineMasterDao.findByNewlineName(resultCalReqDto.getLineName());
            if(resultCalReqDto.getLineOpenNo()!=null) {
                newLineMaster.setLineOpenNo(resultCalReqDto.getLineOpenNo());
                newLineMaster.setLineCloseNo(null);
            }
            if (resultCalReqDto.getLineMidNo()!=null) {
                newLineMaster.setLineMidNo(resultCalReqDto.getLineMidNo());
            }
            if (resultCalReqDto.getLineCloseNo() != null) {
                newLineMaster.setLineCloseNo(resultCalReqDto.getLineCloseNo());
            }
            newLineMasterDao.save(newLineMaster);



        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        try {

            String gameRateName="Single 1 KA";
            String ser = resultCalReqDto.getLineMidNo();
            List<ResultUserResDto> resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());

            System.out.println(" Result wining Result size=="+resultUserResDtoList.size());

            //                    Double amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                    Double finRate=amount/10;
//                    res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),finRate)

            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);


//              gameName="Single Pana";
            gameRateName="Jodi 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());
            System.out.println(" Result wining Result size=="+resultUserResDtoList.size());

            //                    amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                    finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);

//                gameName="Double Pana";
            gameRateName="Single Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());

//                amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);

//                gameName="Triple Pana";
            gameRateName="Double Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());
//                amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);

            gameRateName="Triple Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());
            System.out.println(" Result wining Result size=="+resultUserResDtoList.size());

            //                amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);

            gameRateName="Half Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());
            System.out.println(" Result wining Result size=="+resultUserResDtoList.size());

            //                amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);


            gameRateName="Full Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameRateName, ser, resultCalReqDto.getSession());
            System.out.println(" Result wining Result size=="+resultUserResDtoList.size());

            //                amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//                finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameRateName);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<UserTokenReqDto> list = userMasterDao.getUserTokenList();
            Thread thread = new Thread(() -> {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:aa");
                String strDate = dateFormat.format(new Date());
                for (UserTokenReqDto userTokenReqDto : list) {
                    String token = userTokenReqDto.getToken();
                    String fcmDesc = resultCalReqDto.getLineOpenNo()+"-"+resultCalReqDto.getLineMidNo()+"*-***";
                    String fcmTitle = resultCalReqDto.getLineName();

                    String fcmDate = strDate;
                    FirebaseCloudMessaging firebaseCloudMessaging = new FirebaseCloudMessaging();
                    firebaseCloudMessaging.sendPushNotification(token, fcmDesc, fcmTitle, fcmDate);
                }
            }
            );
            thread.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return res;

    }

    public Boolean insertResult( List<ResultUserResDto> resultUserResDtoList,Integer lineId,String gameName){
        try {

            for (ResultUserResDto resultUserResDto :resultUserResDtoList) {


                Double amount=gameRateUserDao.getGameRateByName(gameName,resultUserResDto.getId());

                System.out.println(gameName+"......ganame");
                System.out.println(amount+".....amount........");

                Integer no=userMasterDao.aprRejAgentFundRequestDao(amount*resultUserResDto.getBidPoint(),resultUserResDto.getId());
                System.out.println(no+"aa");
                UserTransctionHistory userTransctionHistory=new UserTransctionHistory();
                userTransctionHistory.setDateTime(new Date());
                userTransctionHistory.setPoints(amount*resultUserResDto.getBidPoint());
                userTransctionHistory.setTranType("Bid Wining");
                userTransctionHistory.setTranStatus("Credit");

                UserMaster userMaster=new UserMaster();
                userMaster.setId(resultUserResDto.getId());
                userMaster=userMasterDao.getById(resultUserResDto.getId());
                userTransctionHistory.setDate(new Date());
                userTransctionHistory.setUserMaster(userMaster);

                userTransctionHistory.setLineName(gameName);

                userTransctionHistoryDao.save(userTransctionHistory);

                // changes
                TransctionHistory transctionHistory=new TransctionHistory();
                transctionHistory.setPoints(amount*resultUserResDto.getBidPoint());
                transctionHistory.setUserMaster(userMaster);
                transctionHistory.setTranStatus("Credit");
                transctionHistory.setTranType("Add");
                Double totalWinValue=userMaster.getWalletPoints()+transctionHistory.getPoints();
                transctionHistory.setBalance(String.valueOf(totalWinValue));
                transctionHistory.setDateAndTime(new Date());
                transctionHistoryDao.save(transctionHistory);
                System.out.println("  transctionHistory == "+transctionHistory.toString());

                        //

                WiningHistory winingHistory=new WiningHistory();
                winingHistory.setWinDate(new Date());
                winingHistory.setWinPoints(amount*resultUserResDto.getBidPoint());
                winingHistory.setUserMaster(userMaster);
                winingHistory.setWinStatus("Credit In Wallet");
                winingHistory.setDate(new Date());
                winingHistory.setBidPoint(resultUserResDto.getBidPoint());
                winingHistory.setValue(resultUserResDto.getValue());
                winingHistory.setSession(resultUserResDto.getSession());
                System.out.println("  winingHistory == "+winingHistory.toString());


                LineMaster lineMaster=new LineMaster();
                lineMaster.setLineId(lineId);
                winingHistory.setLineMaster(lineMaster);
               WiningHistory winingHistory1=  winingHistoryDao.save(winingHistory);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Boolean clseResultCal(ResultCalReqDto resultCalReqDto) {
        Boolean res=false;
        Integer closeNo=null;
        try{
            Integer no=lineMasterDao.updateCloseResultDetails(resultCalReqDto.getLineId(),resultCalReqDto.getLineOpenNo(),resultCalReqDto.getLineMidNo(),resultCalReqDto.getLineCloseNo());
            NewLineMaster newLineMaster=newLineMasterDao.findByNewlineName(resultCalReqDto.getLineName());
            if(resultCalReqDto.getLineOpenNo()!=null) {
                newLineMaster.setLineOpenNo(resultCalReqDto.getLineOpenNo());
                newLineMaster.setLineCloseNo(null);
            }
            if (resultCalReqDto.getLineMidNo()!=null) {
                newLineMaster.setLineMidNo(resultCalReqDto.getLineMidNo());
            }
            if (resultCalReqDto.getLineCloseNo() != null) {
                newLineMaster.setLineCloseNo(resultCalReqDto.getLineCloseNo());
            }
            newLineMasterDao.save(newLineMaster);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        try {
            String gameName="Single 1 KA";
            Integer sumOfC=getSum(resultCalReqDto.getLineCloseNo());
            String ser = Integer.toString(sumOfC%10);
            List<ResultUserResDto> resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            Double amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            Double finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Single Pana";
            gameName="Jodi 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Double Pana";
            gameName="Single Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Triple Pana";
            gameName="Double Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Jodi Digit";
            gameName="Triple Pana 1 KA";
            ser = resultCalReqDto.getLineMidNo();
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

            gameName="Half Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            sumOfC=getSum(resultCalReqDto.getLineCloseNo());
            ser =ser+"-"+Integer.toString(sumOfC%10);
            System.out.println("Sachin"+ser);
            closeNo=sumOfC%10;
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, "OPEN");
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

            gameName="Full Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            sumOfC=getSum(resultCalReqDto.getLineOpenNo());
            ser =ser+"-"+Integer.toString(sumOfC%10);
            System.out.println("Sachin1"+ser);
            resultUserResDtoList = bidMasterDao.getdemoList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);


//            gameName="Full Sangam";
//            ser = Integer.toString(resultCalReqDto.getLineOpenNo())+"-"+Integer.toString(resultCalReqDto.getLineCloseNo());
//            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
////            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
////            finRate=amount/10;
//            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);
//
//            gameName="SP Motor";
//            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
//            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
////            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
////            finRate=amount/10;
//            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);
//
//            gameName="DP Motor";
//            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
//            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
////            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
////            finRate=amount/10;
//            res=insertResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);
            List<LineMaster> lineMasters=lineMasterDao.getLineMasterStatus("Active");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            List<UserTokenReqDto> list = userMasterDao.getUserTokenList();
            Integer finalCloseNo = closeNo;
            Thread thread = new Thread(() -> {

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:aa");
                String strDate = dateFormat.format(new Date());
                for (UserTokenReqDto userTokenReqDto : list) {
                    String token = userTokenReqDto.getToken();
                    String fcmDesc = resultCalReqDto.getLineOpenNo()+"-"+resultCalReqDto.getLineMidNo() +"-"+resultCalReqDto.getLineCloseNo();
                    String fcmTitle = resultCalReqDto.getLineName();
                    String fcmDate = strDate;
                    FirebaseCloudMessaging firebaseCloudMessaging = new FirebaseCloudMessaging();
                    firebaseCloudMessaging.sendPushNotification(token, fcmDesc, fcmTitle, fcmDate);
                }
            }
            );
            thread.start();
        }

        catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Boolean openResultDebCal(ResultCalReqDto resultCalReqDto) {
        Boolean res=false;

        try {
            String gameName="Single 1 KA";
            String ser = resultCalReqDto.getLineMidNo();
            List<ResultUserResDto> resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            Double amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            Double finRate=amount/10;
//            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),finRate);
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Single Pana";
            gameName="Jodi 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
            System.out.println(resultUserResDtoList.size());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Double Pana";
            gameName="Single Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Triple Pana";
            gameName="Double Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

             gameName="Triple Pana 1 KA";
             ser = resultCalReqDto.getLineMidNo();
          resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            Double amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            Double finRate=amount/10;
//            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),finRate);
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Single Pana";
            gameName="Half Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
            System.out.println(resultUserResDtoList.size());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

            gameName="Full Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
            System.out.println(resultUserResDtoList.size());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;



    }

    @Override
    public Boolean clseResultDebCal(ResultCalReqDto resultCalReqDto) {
        Boolean res=false;
        try {
            String gameName="Single 1 KA";
            Integer sumOfC=getSum(resultCalReqDto.getLineCloseNo());
            String ser = Integer.toString(sumOfC%10);
            List<ResultUserResDto> resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            Double amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            Double finRate=amount/10;
//            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),finRate);
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Single Pana";
            gameName="Jodi 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Double Pana";
            gameName="Single Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Triple Pana";
            gameName="Double Pana 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

//            gameName="Jodi Digit";
            gameName="Triple Pana 1 KA";
            ser = resultCalReqDto.getLineMidNo();
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

            gameName="Half Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineOpenNo());
            sumOfC=getSum(resultCalReqDto.getLineCloseNo());
            ser =ser+"-"+Integer.toString(sumOfC%10);
            System.out.println("Half Sangam"+ser);
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, "OPEN");
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);

            gameName="Full Sangam 1 KA";
            ser = Integer.toString(resultCalReqDto.getLineCloseNo());
            sumOfC=getSum(resultCalReqDto.getLineOpenNo());
            ser =ser+"-"+Integer.toString(sumOfC%10);
            resultUserResDtoList = bidMasterDao.getWinierUserList(resultCalReqDto.getLineId(),gameName, ser, resultCalReqDto.getSession());
//            amount=gameRateMasterDao.getGameRateByName(gameName,resultCalReqDto.getAgentId());
//            finRate=amount/10;
            res=updateResult(resultUserResDtoList,resultCalReqDto.getLineId(),gameName);



        }catch (Exception e){
            e.printStackTrace();
        }
        return res;

    }



    public int getSum(int n)
    {
        int sum = 0;

        while (n != 0)
        {
            sum = sum + n % 10;
            n = n/10;
        }

        return sum;
    }
    public Boolean updateResult( List<ResultUserResDto> resultUserResDtoList,Integer lineId,String gameName){
        try {

            for (ResultUserResDto resultUserResDto :resultUserResDtoList) {
                //hide to remove
                System.out.println("update");
                Double amount=gameRateUserDao.getGameRateByName(gameName,resultUserResDto.getId());

//                Double finRate=amount/10;
                Integer no=userMasterDao.aprRejAgentFundRequestDao(-amount*resultUserResDto.getBidPoint(),resultUserResDto.getId());
                UserTransctionHistory userTransctionHistory=new UserTransctionHistory();
                userTransctionHistory.setDateTime(new Date());
                userTransctionHistory.setPoints(amount*resultUserResDto.getBidPoint());
                userTransctionHistory.setTranType("Bid Wining");
                userTransctionHistory.setTranStatus("Debit");
                UserMaster userMaster=new UserMaster();
                userMaster.setId(resultUserResDto.getId());


                userTransctionHistory.setUserMaster(userMaster);
                userTransctionHistory.setDate(new Date());

                userTransctionHistoryDao.save(userTransctionHistory);

                WiningHistory winingHistory=new WiningHistory();
                winingHistory.setWinDate(new Date());
                winingHistory.setDate(new Date());
                winingHistory.setWinPoints(amount*resultUserResDto.getBidPoint());
                winingHistory.setUserMaster(userMaster);
                winingHistory.setSession(resultUserResDto.getSession());
                winingHistory.setWinStatus("Credit In Wallet");
                winingHistory.setBidPoint(resultUserResDto.getBidPoint());
                winingHistory.setValue(resultUserResDto.getValue());
                LineMaster lineMaster=new LineMaster();
                lineMaster.setLineId(lineId);
                winingHistory.setLineMaster(lineMaster);
                WiningHistory winingHistory1=  winingHistoryDao.save(winingHistory);
                System.out.println(winingHistory1.toString()+"aaaa");




            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }



    @Override
    public Boolean deleteResult(DelectResultReqDto delectResultReqDto) {

        try {


            LineMaster lineMaster = lineMasterDao.getLineMidNo(delectResultReqDto.getLineId(), delectResultReqDto.getLineDate(), delectResultReqDto.getLineName());
//            System.out.println( " MidNo ="+lineMaster.getLineMidNo()+"  size ="+lineMaster.getLineMidNo().length()+"  a= "+lineMaster.getLineMidNo().charAt(0)+" b="+lineMaster.getLineMidNo().charAt(1));

            String midNumber = lineMaster.getLineMidNo();
            List<WiningHistory> winingHistory = winingHistoryDao.getData(delectResultReqDto.getLineId(), delectResultReqDto.getLineDate());
            if (delectResultReqDto.getSession().equals("Open")) {
                System.out.println( " Open Session ");
                char a = midNumber.charAt(0);
                midNumber = midNumber.replace(String.valueOf(a), ""); // Remove 'a' from midNumber

                lineMaster.setLineMidNo(midNumber);
                lineMaster.setLineOpenNo(null);
                lineMasterDao.save(lineMaster);

                for (WiningHistory winingHistory1 : winingHistory) {
//                    char a = midNumber.charAt(0);
//                    midNumber = midNumber.replace(String.valueOf(a), ""); // Remove 'a' from midNumber
//                    lineMaster.setLineMidNo(midNumber);
//                    lineMaster.setLineOpenNo(null);
//                    lineMasterDao.save(lineMaster);

                    UserMaster userMaster = userMasterDao.getByUserIdWise(winingHistory1.getUserMaster().getId());

                    Double walletPoint = userMaster.getWalletPoints();
                    Double walletPoint1 = winingHistory1.getWinPoints();
                    Double w = walletPoint - walletPoint1;
                    userMaster.setWalletPoints(w);
                    userMasterDao.save(userMaster);
                    winingHistory1.setStatus("delete");
                    winingHistoryDao.save(winingHistory1);

                }

            }else if (delectResultReqDto.getSession().equals("Close")) {
                System.out.println( " close Session ");
                char b;
                if(midNumber.length()>1) {
                   b = midNumber.charAt(1);
                }else {
                    b = midNumber.charAt(0);
                }
                midNumber = midNumber.replace(String.valueOf(b), ""); // Remove 'a' from midNumber
                System.out.println(midNumber);
                lineMaster.setLineMidNo(midNumber);
                lineMaster.setLineCloseNo(null);
                lineMasterDao.save(lineMaster);

                for (WiningHistory winingHistory1 : winingHistory) {
//                    char b = midNumber.charAt(0);
//                    midNumber = midNumber.replace(String.valueOf(b), ""); // Remove 'a' from midNumber
//                    System.out.println(midNumber);
//                    lineMaster.setLineMidNo(midNumber);
//                    lineMaster.setLineCloseNo(null);
//                    lineMasterDao.save(lineMaster);

                    UserMaster userMaster = userMasterDao.getByUserIdWise(winingHistory1.getUserMaster().getId());
                    Double walletPoint = userMaster.getWalletPoints();
                    Double walletPoint1 = winingHistory1.getWinPoints();
                    Double w = walletPoint - walletPoint1;
                    userMaster.setWalletPoints(w);
                    System.out.println(w + "walletPoint..............");
                    System.out.println(walletPoint);
                    userMasterDao.save(userMaster);
                    winingHistory1.setStatus("delete");
                    winingHistoryDao.save(winingHistory1);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


}


/*
 @Override
    public Boolean deleteResult(DelectResultReqDto delectResultReqDto) {

        try {


            LineMaster lineMaster = lineMasterDao.getLineMidNo(delectResultReqDto.getLineId(), delectResultReqDto.getLineDate(), delectResultReqDto.getLineName());
            String midNumber = lineMaster.getLineMidNo();
            List<WiningHistory> winingHistory = winingHistoryDao.getData(delectResultReqDto.getLineId(), delectResultReqDto.getLineDate());
            if (delectResultReqDto.getSession().equals("Open")) {
                System.out.println( " Open Session ");


                for (WiningHistory winingHistory1 : winingHistory) {
                    char a = midNumber.charAt(0);
                    midNumber = midNumber.replace(String.valueOf(a), ""); // Remove 'a' from midNumber
                    lineMaster.setLineMidNo(midNumber);
                    lineMaster.setLineOpenNo(null);
                    lineMasterDao.save(lineMaster);

                    UserMaster userMaster = userMasterDao.getByUserIdWise(winingHistory1.getUserMaster().getId());

                    Double walletPoint = userMaster.getWalletPoints();
                    Double walletPoint1 = winingHistory1.getWinPoints();
                    Double w = walletPoint - walletPoint1;
                    userMaster.setWalletPoints(w);
                    userMasterDao.save(userMaster);
                    winingHistory1.setStatus("delete");
                    winingHistoryDao.save(winingHistory1);

                }

            }else if (delectResultReqDto.getSession().equals("Close")) {
                System.out.println( " close Session ");
                for (WiningHistory winingHistory1 : winingHistory) {
                    char b = midNumber.charAt(0);
                    midNumber = midNumber.replace(String.valueOf(b), ""); // Remove 'a' from midNumber
                    System.out.println(midNumber);
                    lineMaster.setLineMidNo(midNumber);
                    lineMaster.setLineCloseNo(null);
                    lineMasterDao.save(lineMaster);

                    UserMaster userMaster = userMasterDao.getByUserIdWise(winingHistory1.getUserMaster().getId());
                    Double walletPoint = userMaster.getWalletPoints();
                    Double walletPoint1 = winingHistory1.getWinPoints();
                    Double w = walletPoint - walletPoint1;
                    userMaster.setWalletPoints(w);
                    System.out.println(w + "walletPoint..............");
                    System.out.println(walletPoint);
                    userMasterDao.save(userMaster);
                    winingHistory1.setStatus("delete");
                    winingHistoryDao.save(winingHistory1);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
 */