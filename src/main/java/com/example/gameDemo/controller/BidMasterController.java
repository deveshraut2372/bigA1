package com.example.gameDemo.controller;


import com.example.gameDemo.model.GameRateMaster;
import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.payload.req.*;
import com.example.gameDemo.payload.res.*;
import com.example.gameDemo.repository.GameRateMasterDao;
import com.example.gameDemo.repository.LineMasterDao;
import com.example.gameDemo.service.BidMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/bidMaster")
public class BidMasterController {

        @Autowired
        private BidMasterService bidMasterService;



    @PostMapping(value = "/createBidMaster")
    public ResponseEntity createBidMaster(@RequestBody List<BidMasterReqDto> bidMasterReqDtoList) {
        BidResDto balance = bidMasterService.createBidMaster(bidMasterReqDtoList);
        if (balance!=null) {
            return new ResponseEntity(balance, HttpStatus.OK);
        } else {
            return new ResponseEntity(balance, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAllList/{id}")
    public ResponseEntity getAllBidRequest(@PathVariable Long id)
    {
        List list = bidMasterService.getAllBidRequest(id);
        if(list.size()!=0)
        {
            return new ResponseEntity(list,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity calResult(@RequestBody ResultCalReqDto resultCalReqDto) {
       // ResultCalculation resultCalculation=new ResultCalculation();
        Boolean flag = bidMasterService.openResultCal(resultCalReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Priya
    @PostMapping(value = "/getBidMasterDetails")
    public ResponseEntity getBidMasterDetails(@RequestBody BidMasterReqDto bidMasterReqDto)
    {
        List list = bidMasterService.getBidMasterDetails(bidMasterReqDto);

        return new ResponseEntity(list,HttpStatus.OK);

    }

    @PostMapping(value = "/getBidValueCount")
    public ResponseEntity getBidValueCount(@RequestBody BidMasterReqDto bidMasterReqDto1)
    {
        List<BidValueCountResDto> bidValueCountResDtos  = bidMasterService.getBidValueCount(bidMasterReqDto1);

        return new ResponseEntity(bidValueCountResDtos,HttpStatus.OK);

    }

    //Priya
    @GetMapping(value = "/getTotalWinningAmount/{lineId}")
    public ResponseEntity getTotalWinningAmount(@PathVariable Integer lineId)
    {
        TotalWinningResDto totalWinningAmount = bidMasterService.getTotalWinningAmount(lineId);

            return new ResponseEntity(totalWinningAmount,HttpStatus.OK);

    }

    //shravani
    //total winingPoints & total bidPoints
    @GetMapping(value = "/getWeeklyListByAgentId/{id}")
    public ResponseEntity getWeeklyListByAgentId(@PathVariable Long id)
    {
        BarchartResDto barchartResDto = bidMasterService.getWeeklyListByAgentId(id);

        return new ResponseEntity(barchartResDto, HttpStatus.OK);

    }

    @GetMapping(value = "/getTodaysBidCount/{id}")
    public ResponseEntity getTodaysBidCount(@PathVariable("id") Long id)
    {
        DashBoardCountResDto dashBoardCountResDto = bidMasterService.getTodaysBidCount(id);

        return new ResponseEntity(dashBoardCountResDto, HttpStatus.OK);

    }

    @GetMapping(value = "/getAllBidMasterList")

    public ResponseEntity getAllBidMasterList()
    {
        List list = bidMasterService.getAllBidMasterList();
        return new ResponseEntity(list,HttpStatus.OK);

    }


    @GetMapping(value = "/getdemoList")

    public ResponseEntity getdemoList(@RequestBody DemoReq demoReq)
    {
        List list = bidMasterService.getdemoList(demoReq);
        return new ResponseEntity(list,HttpStatus.OK);

    }

    @GetMapping(value = "/getBidHistory")
    public ResponseEntity getBidHistory()
    {
        List list = bidMasterService.getBidHistory();
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getBidHistoryByDate")
    public ResponseEntity getBidHistoryByDate(@RequestBody BidMasterDateReq bidMasterDateReq) {
        List list = bidMasterService.getBidHistoryByDate(bidMasterDateReq);
            return new ResponseEntity(list, HttpStatus.OK);

    }

    @PostMapping(value = "/getBusinessDateWise")
    public ResponseEntity getBusinessDateWise(@RequestBody DateReqDto dateReqDto)
    {
        List list = bidMasterService.getBusinessDateWise(dateReqDto);
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/getReportDayWise")
    public ResponseEntity getReportDayWise(@RequestBody DateReqDto dateReqDto)
    {
        if(dateReqDto.getBidDate()==null)
        {
            dateReqDto.setBidDate(new Date());
        }


        List list = bidMasterService.getReportDayWise(dateReqDto);
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getReportPointWise")
    public ResponseEntity getReportPointWise(@RequestBody DateReqDto dateReqDto)
    {
        List list = bidMasterService.getReportPointWise(dateReqDto);
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/getBidReportOpen")
    public ResponseEntity getBidReportOpen(@RequestBody BidReportReqDto bidReportReqDto)
    {


        List list = bidMasterService.getBidReportOpen(bidReportReqDto);
        if(list.size()!=0)
        {
            return new ResponseEntity(list, HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getBidReportClose")
    public ResponseEntity getBidReportClose(@RequestBody BidReportReqDto bidReportReqDto)
    {
        List list = bidMasterService.getBidReportClose(bidReportReqDto);
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
