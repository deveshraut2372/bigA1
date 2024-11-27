package com.example.gameDemo.repository;

import com.example.gameDemo.model.WiningHistory;
import com.example.gameDemo.payload.res.TotalWinningResDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface WiningHistoryDao extends CrudRepository<WiningHistory,Integer> {
    @Query("select new com.example.gameDemo.payload.res.TotalWinningResDto(sum(bh.winPoints),count(bh.winPoints)) from WiningHistory as bh  inner join bh.lineMaster as lm  where lm.lineId=:lineId")
    TotalWinningResDto getTotalWinningAmount(@Param("lineId") Integer lineId);
    @Query("select sum(m.winPoints) FROM WiningHistory m where m.userMaster.id=:id and date(m.winDate)=date(:fromSundayToCurrentDate)")
    Long getSumOfWiningPoint(@Param("fromSundayToCurrentDate") Date fromSundayToCurrentDate,@Param("id") Long id);

    @Query("select wm from WiningHistory as wm where wm.lineMaster.lineId=:lineId and wm.date=:lineDate")
    List<WiningHistory> getData(@Param("lineId")Integer lineId,@Param("lineDate") Date lineDate);


    @Query("select wm from WiningHistory as wm where wm.date=:bidDate")
    List<WiningHistory> getByDAte(@Param("bidDate") Date bidDate);

    @Query("select sum(m.winPoints) FROM WiningHistory m where m.date=:bidDate")
    Double getSumOfBidPoints(@Param("bidDate") Date bidDate);

    @Query("select m.lineMaster.lineName FROM WiningHistory m where m.lineMaster.lineId=:lineId and m.date=:bidDate")
    List<String> getLineName(@Param("lineId")Integer lineId, @Param("bidDate")Date bidDate);


//    @Query("select wm from WiningHistory as wm where wm.lineMaster.lineId=:lineId and wm.date=:lineDate")
//    WiningHistory getData(@Param("lineId")Date lineId,@Param("lineDate") Date lineDate);
}
