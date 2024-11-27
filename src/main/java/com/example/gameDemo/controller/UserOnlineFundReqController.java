package com.example.gameDemo.controller;

import com.example.gameDemo.model.BannerMaster;
import com.example.gameDemo.model.UserOnlineFundReqMaster;
import com.example.gameDemo.payload.req.AddPointsOnlineReqDto;
import com.example.gameDemo.payload.req.BannerMasterReqDto;
import com.example.gameDemo.payload.req.UserOnlineFundReq;
import com.example.gameDemo.payload.res.UserOnlineFundRes;
import com.example.gameDemo.service.UserOnlineFundReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/UserOnlineFundReq")
@CrossOrigin(origins = "*")
public class UserOnlineFundReqController {

    @Autowired
    private UserOnlineFundReqService userOnlineFundReqService;

    @PostMapping
    public ResponseEntity createUserOnlineFundReq(@RequestBody UserOnlineFundReq userOnlineFundReq) {
        Boolean flag = userOnlineFundReqService.createUserOnlineFundReq(userOnlineFundReq);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity updateUserOnlineFundReq(@RequestBody UserOnlineFundReq userOnlineFundReq) {
        Boolean flag = userOnlineFundReqService.updateUserOnlineFundReq(userOnlineFundReq);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity getAll() {
        List<UserOnlineFundRes> userOnlineFundRes = userOnlineFundReqService.getAll();
        return new ResponseEntity(userOnlineFundRes, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAllByStatus/{status}")
    public ResponseEntity getByStatus(@PathVariable("status") String status) {
        List<UserOnlineFundRes> userOnlineFundRes = userOnlineFundReqService.getByStatus(status);
        return new ResponseEntity(userOnlineFundRes, HttpStatus.OK);
    }
    @GetMapping(value = "/getAllByStatusAndUserId/{status}/{id}")
    public ResponseEntity getByStatus(@PathVariable("status") String status,@PathVariable("id") Long id) {
        List<UserOnlineFundReqMaster> userOnlineFundReqMasterList = userOnlineFundReqService.getAllByStatusAndUserId(status,id);
        return new ResponseEntity(userOnlineFundReqMasterList, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllByuserOnlineFundId/{userOnlineFundId}")
    public ResponseEntity getAllByuserOnlineFundId(@PathVariable("userOnlineFundId") Integer userOnlineFundId) {
        UserOnlineFundReqMaster userOnlineFundReqMaster = userOnlineFundReqService.getAllByuserOnlineFundId(userOnlineFundId);
        return new ResponseEntity(userOnlineFundReqMaster, HttpStatus.OK);
    }



    @PostMapping(value = "/AddWallatePointsOnline")
    public ResponseEntity addWallatePointsOnline(@RequestBody AddPointsOnlineReqDto addPointsOnlineReqDto)
    {
        Boolean flag=userOnlineFundReqService.addWallatePointsOnline(addPointsOnlineReqDto);

        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
