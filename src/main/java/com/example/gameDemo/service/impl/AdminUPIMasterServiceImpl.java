package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.AdminUPIMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.AdminUPIReqDto;
import com.example.gameDemo.payload.res.AdminUPIRes;
import com.example.gameDemo.repository.AdminUPIMasterDao;
import com.example.gameDemo.repository.UserRepository;
import com.example.gameDemo.service.AdminUPIMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Date;

@Service
public class AdminUPIMasterServiceImpl implements AdminUPIMasterService {

    @Autowired
    private AdminUPIMasterDao adminUPIMasterDao;

    @Autowired
    private UserRepository userRepository;



    @Override
    public Boolean createAdminUPI(AdminUPIReqDto adminUPIReqDto) {

        AdminUPIMaster adminUPIMaster=new AdminUPIMaster();
        BeanUtils.copyProperties(adminUPIReqDto,adminUPIMaster);
        adminUPIMaster.setStatus("Active");
        adminUPIMaster.setDate(new Date());
        adminUPIMaster.setQrCode(adminUPIReqDto.getQrCode());
        UserMaster userMaster=new UserMaster();
        userMaster=userRepository.getByUserIdWise(adminUPIReqDto.getId());
        adminUPIMaster.setUserMaster(userMaster);

        try
        {
            adminUPIMasterDao.save(adminUPIMaster);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }

    }

    @Override
    public Boolean UpdateAdminUPI(AdminUPIReqDto adminUPIReqDto) {

        AdminUPIMaster adminUPIMaster=new AdminUPIMaster();
        BeanUtils.copyProperties(adminUPIReqDto,adminUPIMaster);
        adminUPIMaster.setQrCode(adminUPIReqDto.getQrCode());
        UserMaster userMaster=new UserMaster();
        userMaster=userRepository.getByUserIdWise(adminUPIReqDto.getId());
        adminUPIMaster.setUserMaster(userMaster);
        adminUPIMaster.setAdminUPIId(adminUPIReqDto.getAdminUPIId());
        adminUPIMaster.setStatus(adminUPIReqDto.getStatus());
        adminUPIMaster.setDate(new Date());
        try
        {
            adminUPIMasterDao.save(adminUPIMaster);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AdminUPIRes getActiveAdminUPI() {
        AdminUPIRes adminUPIRes=new AdminUPIRes();
        adminUPIRes=adminUPIMasterDao.getByStatus("Active");
        return adminUPIRes;
    }

}
