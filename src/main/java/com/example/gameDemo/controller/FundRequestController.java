package com.example.gameDemo.controller;

import com.example.gameDemo.payload.req.AdminAddFundReqDto;
import com.example.gameDemo.payload.req.DateReqDto;
import com.example.gameDemo.payload.req.FundRequestDto;
import com.example.gameDemo.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/agengtFundRequest")
public class FundRequestController {
@Autowired
    private FundService fundService;

    @PostMapping(value = "/createFundRequest")
    public ResponseEntity createFundRequest(@RequestBody FundRequestDto fundRequestDto) {
        Boolean flag = fundService.createFundRequest(fundRequestDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getPendingRequestList/{status}")
    public ResponseEntity getPendingRequestList(@PathVariable String status)
    {
        List list = fundService.getPendingRequestList(status);
            return new ResponseEntity(list,HttpStatus.OK);
    }
    @PutMapping(value = "/updateFundRequest")
    public ResponseEntity updateFundRequest(@RequestBody FundRequestDto fundRequestDto) {
        Boolean flag = fundService.updateFundRequest(fundRequestDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getTransactionHistoryList")
    public ResponseEntity getTransactionHistoryList(@RequestBody DateReqDto dateReqDto)
    {
        List list = fundService.getAgentTransactionHistoryList(dateReqDto);
        return new ResponseEntity(list,HttpStatus.OK);

    }

    @PostMapping(value = "/fundAdd")
    public ResponseEntity fundAdd(@RequestBody AdminAddFundReqDto adminAddFundReqDto) {
        Boolean flag = fundService.fundAdd(adminAddFundReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllTranstionList")
    public ResponseEntity getAllTranstionList()
    {
        List list = fundService.getAllTranstionList();
        if(list.size()!=0)
        {
            return new ResponseEntity(list,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getPendingCount")
    public ResponseEntity getPendingCount() {

        Integer no = fundService.getPendingCount();

        return new ResponseEntity(no, HttpStatus.CREATED);


    }

}
