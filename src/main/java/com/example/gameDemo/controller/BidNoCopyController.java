package com.example.gameDemo.controller;

import com.example.gameDemo.payload.req.bidNoCopyModelReq;
import com.example.gameDemo.payload.res.BidNoCopyRes;
import com.example.gameDemo.payload.res.GamelogicResDto;
import com.example.gameDemo.payload.res.UserTranstionResDto;
import com.example.gameDemo.service.BidNoCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/bidNoCopy")
public class BidNoCopyController {

    //   /bidNoCopy/copyBidNo
    @Autowired
    private BidNoCopyService bidNoCopyService;

    @PostMapping(value = "/copyBidNo")
    public ResponseEntity  copyBidNo(@RequestBody bidNoCopyModelReq bidNoCopyModelReq )
    {
        System.out.println(" copyBidNo is called  ");
        Boolean flag=bidNoCopyService.copyBidNo(bidNoCopyModelReq);

        if(flag) {
        return new ResponseEntity(flag, HttpStatus.CREATED);
        }else {
            return new ResponseEntity(flag, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getCopyBidNo/{userId}")
    public ResponseEntity  getCopyBidNo(@PathVariable("userId") Long userId)
    {
        System.out.println(" getCopyBidNo is called  ");
//        BidNoCopyRes bidNoCopyRes =bidNoCopyService.getCopyBidNo(userId);
        List<GamelogicResDto> gamelogicResDtoList=bidNoCopyService.getCopyBidNo(userId);
        return new ResponseEntity(gamelogicResDtoList,HttpStatus.OK);
    }

}
