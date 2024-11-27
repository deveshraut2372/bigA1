package com.example.gameDemo.repository;


import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.UserTokenReqDto;
import com.example.gameDemo.payload.res.PhoneNoResDto;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserMaster, Long> {

    boolean existsByMobileNo(String mobileNo);

    Optional<UserMaster> findByMobileNo(String mobileNo);

    List<UserMaster> findByStatus(String active);
    @Query("select um.id as id from UserMaster as um where um.mobileNo=:mobileNo")
    Integer checkUserMobileNumber(@Param("mobileNo") String mobileNo);
    @Modifying
    @Transactional
    @Query("update UserMaster as am set am.walletPoints=am.walletPoints -:walletPoints where am.id=:id")
    Integer updatePoints1(@Param("id") Long id, @Param("walletPoints") Double points);

    @Modifying
    @Transactional
    @Query("update UserMaster as am set am.walletPoints=am.walletPoints+:walletPoints  where am.id=:id")
    Integer aprRejAgentFundRequestDao(@Param("walletPoints") double v,@Param("id") Long id);
    @Query("select new  com.example.gameDemo.payload.req.UserTokenReqDto(umm.id,umm.token) from UserMaster as umm")
    List<UserTokenReqDto> getUserTokenList();

    @Query("select um.walletPoints as walletPoints from UserMaster as um where um.id=:id")
    Double findWalletBalance(@Param("id") Long id);

    @Query("select am.commision as commision from UserMaster as am where am.id=:id")
    Integer getAgentCommision(@Param("id") Long id);

    @Query("select new com.example.gameDemo.payload.res.PhoneNoResDto(u.whatsAppNo,u.mobileNo) from UserMaster as u where u.id=:id")
    PhoneNoResDto getPhoneNoUserIdWise(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("update UserMaster as us set us.token=:token where us.id=:id")
    Integer updateTokenByUSerId(@Param("id") Long id, @Param("token") String token);

    @Query("select am.walletPoints as walletPoints from UserMaster as am where am.id=:id")
    Double getId(@Param("id") Long id);

    @Query("select um from UserMaster as um where um.id=:id")
    UserMaster getByUserIdWise(@Param("id") Long id);

    @Query("select um.canPlay from UserMaster as um where um.id=:id")
    String getCanPlayUserIdWise(@Param("id") Long id);

    @Query("select count(m.id) FROM UserMaster as m where date(m.regDate)=date(:date)")
    Integer getUserCount(@Param("date") Date date);

    List<UserMaster> findAllByOrderByIdDesc();


//    @Query("select um.roles from UserMaster as um")
//    List<Role>  getRoleByUserId();
}
