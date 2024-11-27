package com.example.gameDemo.repository;

import com.example.gameDemo.model.TransctionHistory;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.res.FundResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

public interface TransctionDao extends JpaRepository<TransctionHistory,Integer> {

    @Query("select new com.example.gameDemo.payload.res.FundResDto(f.points,f.tranStatus,f.userMaster.id,f.tranType) from TransctionHistory as f where f.dateAndTime=:bidDate")
    List<FundResDto> getAgentTransactionHistoryList(@Param("bidDate") Date bidDate);

    List<TransctionHistory> findAllByOrderByDateAndTimeDesc();


    @Query("select tm from TransctionHistory as tm where Date(tm.dateAndTime)=:date ")
    List<TransctionHistory> getAllByBidDate(@Param("date") Date date);

    @Query("select tm from TransctionHistory as tm where tm.userMaster.id=:id ORDER BY (tm.dateAndTime) DESC ")
    List<TransctionHistory> getAllByUserid(@Param("id") Long id);
}
