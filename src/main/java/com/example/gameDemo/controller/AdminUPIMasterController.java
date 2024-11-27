package com.example.gameDemo.controller;

import com.example.gameDemo.model.AdminUPIMaster;
import com.example.gameDemo.payload.req.AdminUPIReqDto;
import com.example.gameDemo.payload.req.BannerMasterReqDto;
import com.example.gameDemo.payload.res.AdminUPIRes;
import com.example.gameDemo.service.AdminUPIMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/AdminUPIMaster")
@CrossOrigin(origins = "*")
public class AdminUPIMasterController {

    @Autowired
    private AdminUPIMasterService adminUPIMasterService;

    @PostMapping
    public ResponseEntity createAdminUPI(@RequestBody AdminUPIReqDto adminUPIReqDto) {

        Boolean flag = adminUPIMasterService.createAdminUPI(adminUPIReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity UpdateAdminUPI(@RequestBody AdminUPIReqDto adminUPIReqDto) {

        Boolean flag = adminUPIMasterService.UpdateAdminUPI(adminUPIReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/getActiveAdminUPI")
    public ResponseEntity getActiveAdminUPI() {
        AdminUPIRes  adminUPIRes=new AdminUPIRes();
        adminUPIRes= adminUPIMasterService.getActiveAdminUPI();
            return new ResponseEntity(adminUPIRes, HttpStatus.OK);
    }

}
