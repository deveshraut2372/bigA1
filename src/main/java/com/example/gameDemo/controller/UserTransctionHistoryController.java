package com.example.gameDemo.controller;


import com.example.gameDemo.payload.req.DateReqDto;
import com.example.gameDemo.service.UserTransctionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/userTransction")
public class UserTransctionHistoryController {

//  /userTransction/getuserTransction/{id}

    @Autowired
    private UserTransctionHistoryService userTransctionHistoryService;

    // bid poinst history
    @GetMapping(value = "/getuserTransction/{id}")
    public ResponseEntity getuserTransctionList(@PathVariable Long id)
    {
        List list = userTransctionHistoryService.getuserTransctionList(id);
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //
    @PostMapping(value = "/getTransactionReportByDate")
    public ResponseEntity getReportPointWise(@RequestBody DateReqDto dateReqDto)
    {

        List list = userTransctionHistoryService.getReportPointWise(dateReqDto);
            return new ResponseEntity(list, HttpStatus.OK);
    }


    // credit Debit history
    @GetMapping(value = "/getTransction/{id}")
    public ResponseEntity getTransction(@PathVariable Long id)
    {
        List list = userTransctionHistoryService.getTransction(id);
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
