package com.example.gameDemo.service;

import com.example.gameDemo.model.AdminUPIMaster;
import com.example.gameDemo.payload.req.AdminUPIReqDto;
import com.example.gameDemo.payload.res.AdminUPIRes;

public interface AdminUPIMasterService {
    Boolean createAdminUPI(AdminUPIReqDto adminUPIReqDto);

    Boolean UpdateAdminUPI(AdminUPIReqDto adminUPIReqDto);

    AdminUPIRes getActiveAdminUPI();
}
