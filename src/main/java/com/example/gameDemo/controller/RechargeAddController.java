package com.example.gameDemo.controller;

import com.example.gameDemo.payload.req.BannerMasterReqDto;
import com.example.gameDemo.payload.req.RechargeAddReq;
import com.example.gameDemo.service.RechargeAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rechargeAddMaster")
public class RechargeAddController {
    @Autowired
    private RechargeAddService rechargeAddService;

    @PostMapping(value = "/createRechargeAdd")
    public ResponseEntity createRechargeAdd(@RequestBody List<RechargeAddReq>  rechargeAddReq) {
        Boolean flag = rechargeAddService.createRechargeAdd(rechargeAddReq);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateRechargeAdd")
    public ResponseEntity updateRechargeAdd(@RequestBody RechargeAddReq rechargeAddReq) {
        Boolean flag = rechargeAddService.updateRechargeAdd(rechargeAddReq);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllRechargeAddMasterList")
    public ResponseEntity getAllRechargeAddMasterList()
    {
        List list = rechargeAddService.getAllBannerMasterList();
        return new ResponseEntity(list,HttpStatus.OK);

    }

}
