package com.example.gameDemo.controller;

import com.example.gameDemo.payload.req.DelectResultReqDto;
import com.example.gameDemo.payload.req.ResultCalReqDto;
import com.example.gameDemo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/result")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PutMapping(value = "/calResult")
    public ResponseEntity calResult(@RequestBody ResultCalReqDto resultCalReqDto) {
        // ResultCalculation resultCalculation=new ResultCalculation();
        Boolean flag=false;
//        Boolean flag=false;
        if(resultCalReqDto.getSession().equalsIgnoreCase("OPEN")){
            flag = resultService.openResultCal(resultCalReqDto);
        }
        if(resultCalReqDto.getSession().equalsIgnoreCase("CLOSE")){
            flag = resultService.clseResultCal(resultCalReqDto);
        }
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateResult")
    public ResponseEntity calDebitResult(@RequestBody ResultCalReqDto resultCalReqDto) {
        // ResultCalculation resultCalculation=new ResultCalculation();
        Boolean flag=false;
        if(resultCalReqDto.getSession().equalsIgnoreCase("OPEN")){
            flag = resultService.openResultDebCal(resultCalReqDto);
        }
        if(resultCalReqDto.getSession().equalsIgnoreCase("CLOSE")){
            flag = resultService.clseResultDebCal(resultCalReqDto);
        }
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping(value = "/deleteResult")
    public ResponseEntity deleteResult(@RequestBody DelectResultReqDto delectResultReqDto) {

        System.out.println("  delectResultReqDto =="+delectResultReqDto.toString());
        Boolean flag = resultService.deleteResult(delectResultReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
