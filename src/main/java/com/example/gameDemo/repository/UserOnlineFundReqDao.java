package com.example.gameDemo.repository;

import com.example.gameDemo.model.UserOnlineFundReqMaster;
import com.example.gameDemo.payload.res.UserOnlineFundRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserOnlineFundReqDao extends JpaRepository<UserOnlineFundReqMaster,Integer> {


    List<UserOnlineFundReqMaster> findAllByStatus(String status);

    @Query("select ufm from UserOnlineFundReqMaster as ufm where ufm.status=:status and ufm.userMaster.id=:id ")
    List<UserOnlineFundReqMaster> getAllByStatusAndUserId(@Param("status") String status,@Param("id") Long id);

    @Query("select um  from UserOnlineFundReqMaster as um where um.userOnlineFundId=:userOnlineFundId")
    UserOnlineFundReqMaster getAllByuserOnlineFundId(@Param("userOnlineFundId") Integer userOnlineFundId);

    @Query("Select new com.example.gameDemo.payload.res.UserOnlineFundRes(ufm.userOnlineFundId,ufm.status,ufm.date,ufm.paymentScreenShort,ufm.userMaster.username,ufm.userMaster.mobileNo,ufm.userMaster.id) from UserOnlineFundReqMaster as ufm  ")
    List<UserOnlineFundRes> getAll();
    @Query("Select new com.example.gameDemo.payload.res.UserOnlineFundRes(ufm.userOnlineFundId,ufm.status,ufm.date,ufm.paymentScreenShort,ufm.userMaster.username,ufm.userMaster.mobileNo,ufm.userMaster.id) from UserOnlineFundReqMaster as ufm where ufm.status=:status ")
    List<UserOnlineFundRes> getAllByStatus(@Param("status") String status);
}
