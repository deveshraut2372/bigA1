package com.example.gameDemo.service;


import com.example.gameDemo.model.Role;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.SignupRequest;
import com.example.gameDemo.payload.req.UserTokenReqDto;
import com.example.gameDemo.payload.res.OtpResDto;
import com.example.gameDemo.payload.res.PhoneNoResDto;
import com.example.gameDemo.payload.res.UserDetailsResponse;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserMasterService {


    List getAllUsers();

    List<UserMaster> getAllActiveUser();


    List<Role> getRoleByUserId();

    OtpResDto checkUserMobileNumber(String mobNo) throws IOException, NoSuchAlgorithmException, KeyManagementException;

    UserMaster editAdminSetting(Long id);



    Boolean UpdateUserMaster(SignupRequest signupRequest);

    PhoneNoResDto getPhoneNoUserIdWise(Long id);

    Double getWalletPointsUserIdWise(Long id);

    Integer updateUserToken(UserTokenReqDto userTokenReqDto);

    String getCanPlayUserIdWise(Long id);


    Integer getUserCount();

    Boolean updateUserPoint(Long id, String canPlay);

    UserDetailsResponse userDetails(Long id);
}
