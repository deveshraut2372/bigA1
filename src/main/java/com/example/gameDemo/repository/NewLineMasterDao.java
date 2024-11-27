package com.example.gameDemo.repository;


import com.example.gameDemo.model.NewLineMaster;
import com.example.gameDemo.payload.res.NewLineMasterResDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface NewLineMasterDao extends CrudRepository<NewLineMaster,Integer> {


    List findAllByStatus(String active);

//    @Modifying
//    @Transactional
//    @Query(value = "from NewLineMaster where :stringDate='1'    ",nativeQuery = true)
//    List getDayWise1(@Param("stringDate") String stringDate2);



    List findBySundayAndStatus(Boolean flag, String active);

    List findBySaturdayAndStatus(Boolean flag, String active);

    List findByFridayAndStatus(Boolean flag, String active);

    List findByThursdayAndStatus(Boolean flag, String active);

    List findByWednesdayAndStatus(Boolean flag, String active);

    List findByTuesdayAndStatus(Boolean flag, String active);

    List findByMondayAndStatus(Boolean flag, String active);

    @Query("select nm from NewLineMaster as nm")
List<NewLineMaster> getNewLineData();

    List<NewLineMaster> findByNewlineIdAndStatus(Integer newlineId, String active);

    @Query("select nm from NewLineMaster as nm where nm.status='Active'")
    List<NewLineMaster> getNewLineDataStatus( String active);

    @Query("select new  com.example.gameDemo.payload.res.NewLineMasterResDto(nm.newlineId,nm.newlineName,nm.newlineStartTime,nm.newlineEndTime,nm.status,nm.monday,nm.tuesday,nm.wednesday,nm.thursday,nm.friday,nm.saturday,nm.sunday,nm.lineOpenNo,nm.lineMidNo,nm.lineCloseNo)from NewLineMaster as nm where nm.newlineId=:newlineId")
    NewLineMasterResDto getByID(@Param("newlineId") Integer newlineId);



//    List findAllByStatusOrderByNewLineStartTimeDesc(String active);

//    List findAllByStatusOrderByNewlineStartTimeDesc(String active);

    List<NewLineMaster> findByStatusAndLineTime(String active, Object o);

    @Transactional
    @Modifying
    @Query("update NewLineMaster as nm set nm.lineTime=:date1 where nm.newlineId=:newlineId")
    Integer updateLineTime(@Param("date1") Date date1, @Param("newlineId") Integer newlineId);

    List findAllByStatusOrderByLineTimeAsc(String active);

    NewLineMaster findByNewlineName(String lineName);


//    @Query("select nm from NewLineMaster as nm where nm.status='Active' and nm.lineTime='null'")
//    List<NewLineMaster> getList();
}
