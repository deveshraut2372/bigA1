package com.example.gameDemo.repository;

import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.res.LineStatusResDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface LineMasterDao extends CrudRepository<LineMaster,Integer> {

//    List findAllByStatus(String active);
//
//    @Modifying
//    @Transactional
//    @Query("update LineMaster as lm set lm.lineOpenNo=:lineOpenNo,lm.lineMidNo=:lineMidNo,lm.lineCloseNo=:lineCloseNo,lm.openStatus=false  where lm.lineId=:lineId")
//    Integer updateResultDetails(@Param("lineId") Integer lineId, @Param("lineOpenNo") Integer lineOpenNo, @Param("lineMidNo") String lineMidNo, @Param("lineCloseNo") Integer lineCloseNo);

//    @Modifying
//    @Transactional
//    @Query("update LineMaster as lm set lm.lineOpenNo=:lineOpenNo,lm.lineMidNo=:lineMidNo,lm.lineCloseNo=:lineCloseNo,lm.finalStatus=false  where lm.lineId=:lineId")
//    Integer updateCloseResultDetails(@Param("lineId") Integer lineId, @Param("lineOpenNo") Integer lineOpenNo, @Param("lineMidNo") String lineMidNo, @Param("lineCloseNo") Integer lineCloseNo);

    @Query("select new com.example.gameDemo.payload.res.LineStatusResDto(lm.openStatus as openStatus,lm.finalStatus as finalStatus) from LineMaster as lm where lm.lineId=:lineId")
    LineStatusResDto findByLineId(@Param("lineId") Integer lineId);


//    @Modifying
//    @Transactional
//    @Query("update LineMaster as lm set lm.status='Inactive' where date(lm.lineDate)=date(:lineDate)")
//    Integer updateStatusToInActive(@Param("lineDate") Date lineDate);

//    @Modifying
//    @Transactional
//    @Query("update LineMaster as lm set lm.status='Inactive' where lm.lineId=:lineId")
//    Integer updateStatusToInActive1(@Param("lineId") Integer lineId);
//
    List findAllByLineName(String name);
//
//    List findAllByAgentMasterOrderByLineDate(AgentMaster agentMaster);
//
//
//    @Query("select lm from LineMaster as lm")
//    List<LineMaster> getLineMaster();

    @Query("select lm from LineMaster as lm where lm.lineId=:lineId")
    LineMaster getById(@Param("lineId") Integer lineId);


//    List<LineMaster> findAllByDailyStatusAndStatusOrderByLineStartTimeAsc(boolean b, String active);

    @Query("select lm from LineMaster as lm where lm.status='Active'")
    List<LineMaster> getLineMasterStatus(String active);


//    @Query("select lm.lineOpenNo,lm.lineMidNo,lm.lineCloseNo from LineMaster as lm where lm.lineName=:lineName and lm.status='Active'")
//    LineMaster getLineMasterStatusof(@Param("lineName") String lineName);
//
////    List<LineMaster> findAllByDailyStatusAndStatusOrderByLineStartTimeAsc(boolean b, String active);
//
//    @Query("select lm from LineMaster as lm where lm.lineStartTime=:date")
//    List<LineMaster> getList(@Param("date") String date);

    List<LineMaster> findByStatusAndLineTime(String active, Object o);

    @Transactional
    @Modifying
    @Query("update LineMaster as lm set lm.lineTime=:date1 where lm.lineId=:lineId")
    Integer updateLineTime(@Param("date1") Date date1, @Param("lineId") Integer lineId);

//

    @Query("select lm from LineMaster as lm")
    List<LineMaster> getLineMaster();

    @Modifying
    @Transactional
    @Query("update LineMaster as lm set lm.status='Inactive' where lm.lineId=:lineId")
    Integer updateStatusToInActive1(@Param("lineId") Integer lineId);

    List<LineMaster> findAllByDailyStatusAndStatusOrderByLineTimeAsc(boolean b, String active);


    List<LineMaster> findAllByUserMasterOrderByLineDate(Long id);
    @Modifying
    @Transactional
    @Query("update LineMaster as lm set lm.lineOpenNo=:lineOpenNo,lm.lineMidNo=:lineMidNo,lm.lineCloseNo=:lineCloseNo,lm.openStatus=false  where lm.lineId=:lineId")
    Integer updateResultDetails(@Param("lineId") Integer lineId, @Param("lineOpenNo") Integer lineOpenNo, @Param("lineMidNo") String lineMidNo,@Param("lineCloseNo") Integer lineCloseNo);

    @Modifying
    @Transactional
    @Query("update LineMaster as lm set lm.lineOpenNo=:lineOpenNo,lm.lineMidNo=:lineMidNo,lm.lineCloseNo=:lineCloseNo,lm.finalStatus=false  where lm.lineId=:lineId")
    Integer updateCloseResultDetails(@Param("lineId") Integer lineId,@Param("lineOpenNo") Integer lineOpenNo, @Param("lineMidNo") String lineMidNo,@Param("lineCloseNo") Integer lineCloseNo);


    @Query("select lm from LineMaster as lm where lm.lineName=:name")
    List<LineMaster> getLineMasterName(@Param("name") String name);


    @Query("select lm from LineMaster as lm where lm.lineId=:lineId and  lm.lineDate=:lineDate and lm.lineName=:lineName")
    LineMaster getLineMidNo(@Param("lineId") Integer lineId,@Param("lineDate") Date lineDate,@Param("lineName") String lineName);


}

