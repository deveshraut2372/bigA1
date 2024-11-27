package com.example.gameDemo.controller;

import com.example.gameDemo.payload.req.GameLogicReq;
import com.example.gameDemo.payload.req.GameRateMasterReqDto;
import com.example.gameDemo.payload.res.GameRateMasterResDto;
import com.example.gameDemo.payload.res.GameRateUserWiseResDto;
import com.example.gameDemo.payload.res.GamelogicResDto;
import com.example.gameDemo.service.GameLogicService;
import com.example.gameDemo.service.GameRateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/gameLogicMaster")
public class GameLogicController {
 @Autowired
    private GameLogicService singlePanna;

    @PostMapping(value = "/allGameList")
    public ResponseEntity allGameList(@RequestBody GameLogicReq gameLogicReq) {
        System.out.println(" GameLogicReq ==="+gameLogicReq.toString());
       List list =new ArrayList();
       list.removeAll(list);
       list=singlePanna.allGameList(gameLogicReq);
        System.out.println(" list ==="+list);
        System.out.println("  SIZE 1 ="+list.size());

//        Collections.sort(list, new Comparator<GamelogicResDto>() {
//            public int compare(GamelogicResDto c1, GamelogicResDto c2) {
//                if (Integer.parseInt(c1.getBidNo()) > Integer.parseInt(c2.getBidNo())) return 1;
//                if (Integer.parseInt(c1.getBidNo()) < Integer.parseInt(c2.getBidNo())) return -1;
//                return 0;
//            }});

        if(gameLogicReq.getType().compareTo("default")==0||gameLogicReq.getType().compareTo("otc")==0||gameLogicReq.getType().compareTo("spchaukada")==0||gameLogicReq.getType().compareTo("dptchaukada")==0||gameLogicReq.getType().compareTo("bktBracket")==0)
        {
            return new ResponseEntity(list, HttpStatus.CREATED);
        }else{
            for (Object o : list) {
                System.out.println("  list 1="+o.toString());
            }

            Collections.sort(list, new Comparator<GamelogicResDto>() {
                public int compare(GamelogicResDto c1, GamelogicResDto c2) {
                    if (Integer.parseInt(c1.getBidNo()) > Integer.parseInt(c2.getBidNo())) return 1;
                    if (Integer.parseInt(c1.getBidNo()) < Integer.parseInt(c2.getBidNo())) return -1;
                    return 0;
                }});
        }

            return new ResponseEntity(list, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getCurrentTime1")
    public ResponseEntity getCurrentTime1()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        return new ResponseEntity(currentTime, HttpStatus.OK);
    }


    @PostMapping(value = "/removeObject")
    public ResponseEntity removeObject(@RequestBody GameLogicReq gameLogicReq) {
        List list = singlePanna.removeObject(gameLogicReq);

        return new ResponseEntity(list, HttpStatus.CREATED);

    }

    @RestController
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/gameratemaster")
    public static class GameRateMasterController {

        @Autowired
        private GameRateMasterService gameRateMasterService;

        @PostMapping(value = "/createGameRateMaster")
        public ResponseEntity createGameRateMaster(@RequestBody GameRateMasterReqDto gameRateMasterReqDto) {
            Boolean flag = gameRateMasterService.createGameRateMaster(gameRateMasterReqDto);
            if (flag) {
                return new ResponseEntity(flag, HttpStatus.CREATED);
            } else {
                return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping(value = "/updateGameRateMaster")
        public ResponseEntity updateGameRateMaster(@RequestBody GameRateMasterReqDto gameRateMasterReqDto) {
            Boolean flag = gameRateMasterService.updateGameRateMaster(gameRateMasterReqDto);
            if (flag) {
                return new ResponseEntity(flag, HttpStatus.CREATED);
            } else {
                return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping(value = "/getAllList")
        public ResponseEntity getAllList()
        {
            List list = gameRateMasterService.getAllList();

                return new ResponseEntity(list,HttpStatus.OK);

        }

        @GetMapping(value = "/getActiveList")
        public ResponseEntity getActiveList()
        {
            List list = gameRateMasterService.getActiveList();
            if(list.size()!=0)
            {
                return new ResponseEntity(list,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping(value = "/getActiveListGameRateUser/{id}")
        public ResponseEntity getActiveListGameRateUser(@PathVariable Long id)
        {

            List list = gameRateMasterService.getActiveListGameRateUser(id);
            if(list.size()!=0)
            {
                return new ResponseEntity(list,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping(value = "/getGameRateUserMasterIdWise/{gameRateUserMasterId}")
        public ResponseEntity getGameRateUserMasterIdWise(@PathVariable Integer gameRateUserMasterId)
        {
            GameRateUserWiseResDto list = gameRateMasterService.getGameRateUserMasterIdWise(gameRateUserMasterId);

                return new ResponseEntity(list,HttpStatus.OK);
            }




        @GetMapping(value = "/getByIdList/{gameRateMasterId}")
        public ResponseEntity getByIdList(@PathVariable Integer gameRateMasterId)
        {
            GameRateMasterResDto gameRateMasterResDto = gameRateMasterService.getByIdList(gameRateMasterId);

            return new ResponseEntity(gameRateMasterResDto,HttpStatus.OK);

        }

        //shravani
    //    @PostMapping(value = "/getGameRate")
    //    public ResponseEntity getGameRate(@RequestBody GameNameAgentIdReqDto gameNameAgentIdReqDto)
    //    {
    //        Double gameRate = gameRateMasterService.getGameRate(gameNameAgentIdReqDto);
    //
    //        return new ResponseEntity(gameRate,HttpStatus.OK);
    //
    //    }
    //
    //    //shravani
    //    @GetMapping(value = "/getGameRateListByUserId/{id}/{}")
    //    public ResponseEntity getGameRateListByUserId(@PathVariable Long id)
    //    {
    //        List list = gameRateMasterService.getGameRateListByUserId(id);
    //
    //        return new ResponseEntity(list,HttpStatus.OK);
    //
    //    }
    //
        @PutMapping(value = "/updateUserIdWiseGameRate/{id}/{gameRate}/{gameRateMasterId}")
        public ResponseEntity updateUserIdWiseGameRate(@PathVariable Long id, @PathVariable Double gameRate, @PathVariable Integer gameRateMasterId) {
            Boolean flag = gameRateMasterService.updateUserIdWiseGameRate(id,gameRate,gameRateMasterId);
            if (flag) {
                return new ResponseEntity(flag, HttpStatus.CREATED);
            } else {
                return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
