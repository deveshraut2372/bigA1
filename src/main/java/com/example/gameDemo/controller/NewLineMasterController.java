package com.example.gameDemo.controller;



import com.example.gameDemo.model.NewLineMaster;
import com.example.gameDemo.payload.res.NewLineMasterResDto;
import com.example.gameDemo.service.NewLineMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/newlineMaster")
@EnableScheduling
public class NewLineMasterController
{
    @Autowired
    private NewLineMasterService newLineMasterService;


    @PostMapping(value = "/createNewlineMaster")
    public ResponseEntity createNewlineMaster(@RequestBody NewLineMaster newLineMaster) throws ParseException {
        Boolean flag = newLineMasterService.createNewlineMaster(newLineMaster);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateNewLineMaster")
    public ResponseEntity updateNewLineMaster(@RequestBody NewLineMaster newLineMaster) throws ParseException {
        Boolean flag = newLineMasterService.updateNewLineMaster(newLineMaster);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/getActiveListOfNewLineMaster")
    public ResponseEntity getActiveLines()
    {
        List list = newLineMasterService.getActiveListOfNewLineMaster();
//        if(list.size()!=0)
//        {
            return new ResponseEntity(list,HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }


    @GetMapping(value = "/getAllNewLineMaster")
    public ResponseEntity getAllNewLineMaster()
    {
        List list = newLineMasterService.getAllNewLineMaster();
//        if(list.size()!=0)
//        {
            return new ResponseEntity(list,HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }


    @GetMapping(value = "/editNewLine/{newlineId}")
    public ResponseEntity editNewLine(@PathVariable Integer newlineId)
    {
        NewLineMasterResDto newLineMasterResDto = newLineMasterService.editNewLine(newlineId);
        if(newLineMasterResDto!=null)
        {
            return new ResponseEntity(newLineMasterResDto, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(newLineMasterResDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "deleteByNewLineId/{newlineId}")
    private ResponseEntity deleteByNewLineId(@PathVariable Integer newlineId){
        Boolean flag = newLineMasterService.deleteByNewLineId(newlineId);
        return new ResponseEntity(flag, HttpStatus.OK);
    }


//    @Scheduled(cron = "0 0 1 * * *") // 1 AM every Day
//    @Scheduled(cron = "0 * * * * ?")// every min
    @GetMapping(value = "/scheduleLineMaster")
    private ResponseEntity scheduleLineMaster()
    {
        Boolean flag = newLineMasterService.scheduleLineMaster();
        if(flag){
            return new ResponseEntity(flag, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity(flag,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Scheduled(cron = "0 0 1 * * *")// 1 AM every Day
    @GetMapping(value = "/getDayWiseData")
    public ResponseEntity getDayWiseData()
    {
        List list = newLineMasterService.getDayWiseData();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/getResultNewLine")
    public ResponseEntity getResultNewLine()
    {
        List list = newLineMasterService.getResultNewLine();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping(value = "/addLineTime")
    public ResponseEntity addLineTime() throws ParseException {
        Boolean flag=newLineMasterService.addLineTime();
        return new ResponseEntity(flag,HttpStatus.OK);
    }
}
