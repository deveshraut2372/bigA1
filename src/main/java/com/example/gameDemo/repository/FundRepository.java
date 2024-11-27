package com.example.gameDemo.repository;

import com.example.gameDemo.model.FoundMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.FundRequestDto;
import com.example.gameDemo.payload.res.FundResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface FundRepository extends JpaRepository<FoundMaster, Integer> {



    List findAllByStatusOrderByDateTime(String status);

    @Modifying
    @Transactional
    @Query("update FoundMaster as afr set afr.status=:status , afr.reason=:reason where afr.fundRequestId=:fundRequestId")
    Integer updateAgentFundRequest(@Param("status") String status,@Param("reason") String reason,@Param("fundRequestId") Integer fundRequestId);

    @Query("select count(m.id) FROM FoundMaster as m where m.status='Pending'")
    Integer getPendingCount();
}
