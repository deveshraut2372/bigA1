package com.example.gameDemo.repository;

import com.example.gameDemo.model.BidHistory;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.res.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BidMasterDao extends CrudRepository<BidHistory,Integer> {

    @Query("select new com.example.gameDemo.payload.res.ResultUserResDto(um.id as id,um.username as fullName,um.mobileNo as mobileNo,bh.bidPoint as bidPoint,bh.value,bh.session) from BidHistory as bh inner join bh.userMaster as um where bh.lineMaster.lineId=:lineId and bh.gameRateName=:gameRateName and bh.value=:value and bh.session=:session")
    List<ResultUserResDto> getWinierUserList(@Param("lineId") Integer lineId,@Param("gameRateName") String gameRateName, @Param("value") String lineMidNo, @Param("session") String session);

    List findAllByUserMasterOrderByBidDateDesc(UserMaster userMaster);
    @Query("select new com.example.gameDemo.payload.res.BidMasterAgentWiseResDto(um.username as username,um.mobileNo as mobileNo,bh.bidPoint as bidPoint,bh.bidDate as bidDate,um.walletPoints as walletPoints,bh.value as value,bh.session as session) from BidHistory as bh inner join bh.userMaster as um  inner join bh.lineMaster as lm  where um.id=:id and lm.lineId=:lineId  and bh.game=:gameName and lm.lineName=:lineName")
    List getBidMasterDetails(@Param("id") Long id, @Param("lineId") Integer lineId,@Param("game") String gameName, @Param("lineName") String lineName);

    @Query("select new com.example.gameDemo.payload.res.BidValueCountResDto(bh.value as value,count(bh),sum(bh.bidPoint),bh.session as session) from BidHistory as bh inner join bh.userMaster as um inner join bh.lineMaster as lm  where lm.lineId=:lineId and um.id=:id and bh.game=:gameName and lm.lineName=:lineName group by bh.value,bh.session")
    List<BidValueCountResDto> getBidValueCount(@Param("id") Long id, @Param("lineId") Integer lineId,@Param("game") String gameName,@Param("lineName") String lineName);
    @Query("select new com.example.gameDemo.payload.res.TotalWinningResDto(count(bh.bidPoint),sum(bh.bidPoint)) from BidHistory as bh  inner join bh.lineMaster as lm  where lm.lineId=:lineId")
    TotalWinningResDto getTotalWinningAmount(@Param("lineId") Integer lineId);
    @Query("select sum(m.bidPoint) FROM BidHistory m where m.userMaster.id=:id and date(m.bidDate)=date(:fromSundayToCurrentDate)")
    Long getSumOfBidPoint(@Param("fromSundayToCurrentDate") Date fromSundayToCurrentDate,@Param("id") Long id);

    @Query("select count(m.bidId) FROM BidHistory m where m.userMaster.id=:id and date(m.bidDate)=date(:date)")
    Long getTodaysBidCount(@Param("id") Long id,@Param("date") Date date);

    @Query("select new  com.example.gameDemo.payload.res.BidMasterResDto(bm.bidId,bm.bidDate,bm.game,bm.bidPoint,bm.value,bm.session,bm.status,bm.userMaster.id,bm.lineMaster.lineId) from BidHistory as bm where bm.status='Active'")
    List<BidMasterResDto> getAllBidMasterList();

    @Query("select new com.example.gameDemo.payload.res.ResultUserResDto(um.id as id,um.username as fullName,um.mobileNo as mobileNo,bh.bidPoint as bidPoint,bh.value,bh.session) from BidHistory as bh inner join bh.userMaster as um where bh.lineMaster.lineId=:lineId and bh.gameRateName=:gameRateName and bh.value=:value and bh.session=:session")
    List<ResultUserResDto> getdemoList(@Param("lineId") Integer lineId, @Param("gameRateName") String gameRateName, @Param("value") String value, @Param("session") String session);

    List<BidHistory> findAllByBidDate(Date bidDate);


    @Query("select sum(m.bidPoint) FROM BidHistory m where m.lineMaster.lineId=:lineId and m.bidDate=:bidDate")
    Double getWalletPoint(@Param("lineId") Integer lineId,@Param("bidDate") Date bidDate);



    @Query("select sum(m.bidPoint) FROM BidHistory m where m.bidDate=:bidDate")
    Double getSumOfBidPoints(@Param("bidDate") Date bidDate);



    @Query("select m.lineMaster.lineName FROM BidHistory m where m.lineMaster.lineId=:lineId and m.bidDate=:bidDate")
      List<String>  getLineName(@Param("lineId") Integer lineId, @Param("bidDate") Date bidDate);

    @Query("select m FROM BidHistory as m where m.bidDate=:bidDate and m.gameRateName=:gameRateName and m.lineMaster.lineName=:lineName and m.session='Open'")
    List<BidHistory> getBidReportOpen(@Param("bidDate") Date bidDate,@Param("gameRateName") String gameRateName, @Param("lineName") String lineName);
    @Query("select m.value FROM BidHistory m where m.bidDate=:bidDate and m.gameRateName=:gameRateName and m.lineMaster.lineName=:lineName and  m.session='Open'")
    List<String> getLineNameWise(@Param("bidDate") Date bidDate,@Param("gameRateName") String gameRateName,@Param("lineName") String lineName);


    @Query("select sum(m.bidPoint) FROM BidHistory as m where m.value=:lineNameStr and m.bidDate=:bidDate and m.gameRateName=:gameRateName and m.session=:session")
    Double calculateBidPoints(@Param("lineNameStr") String lineNameStr,@Param("bidDate") Date bidDate, @Param("gameRateName") String gameRateName, @Param("session") String session);

    @Query("select m FROM BidHistory as m where m.bidDate=:bidDate and m.gameRateName=:gameRateName and m.lineMaster.lineName=:lineName and m.session='Close'")
    List<BidHistory> getBidReportClose(@Param("bidDate") Date bidDate,@Param("gameRateName") String gameRateName, @Param("lineName") String lineName);

    @Query("select m.value FROM BidHistory m where m.bidDate=:bidDate and m.gameRateName=:gameRateName and m.lineMaster.lineName=:lineName and  m.session='Close'")
    List<String> getLineNameWises(@Param("bidDate") Date bidDate,@Param("gameRateName") String gameRateName,@Param("lineName") String lineName);

    @Query("select sum(m.bidPoint) FROM BidHistory m where m.bidDate=:bidDate and m.gameRateName=:gameRateName  and m.session='Open'")
    Double getSumOfBidPointsOpen(@Param("bidDate") Date bidDate,@Param("gameRateName") String gameRateName);

    @Query("select sum(m.bidPoint) FROM BidHistory m where m.bidDate=:bidDate and m.gameRateName=:gameRateName and m.session='Close'")
    Double getSumOfBidPointClose(@Param("bidDate")Date bidDate,@Param("gameRateName")  String gameRateName);



}

