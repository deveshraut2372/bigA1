package com.example.gameDemo.controller;



import com.example.gameDemo.model.BannerMaster;
import com.example.gameDemo.payload.req.BannerMasterReqDto;
import com.example.gameDemo.service.BannerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/bannerMaster")
public class BannerMasterController {
    
    //shravani
    @Autowired
    private BannerMasterService bannerMasterService;

    @PostMapping(value = "/createBannerMaster")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createBannerMaster(@RequestBody BannerMasterReqDto bannerMasterReqDto) {
        System.out.println("hiiiiiiiiiiiiii");
        Boolean flag = bannerMasterService.createLBannerMaster(bannerMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateBannerMaster")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateBannerMaster(@RequestBody BannerMasterReqDto bannerMasterReqDto) {
        Boolean flag = bannerMasterService.updateBannerMaster(bannerMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getActiveList")
    public ResponseEntity getActiveBannerMaster()
    {
        List<BannerMaster> list = bannerMasterService.getActiveBannerMaster();
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @GetMapping(value = "/getAllBannerMasterList")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @PreAuthorize("hasRole('ADMIN') && hasRole('USER') ")
    public ResponseEntity getAllBannerMasterList()
    {
        System.out.println(" Banner List is called ");
        List list = bannerMasterService.getAllBannerMasterList();
        return new ResponseEntity(list,HttpStatus.OK);

    }
    @GetMapping(value = "/editBannerMaster/{bannerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity editBannerMaster(@PathVariable Integer bannerId)
    {
        BannerMaster bannerMasterResDto = bannerMasterService.editBannerMaster(bannerId);
        if(bannerMasterResDto!=null)
        {
            return new ResponseEntity(bannerMasterResDto, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(bannerMasterResDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
