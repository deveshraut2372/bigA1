package com.example.gameDemo.controller;


import com.example.gameDemo.model.NotificationMaster;
import com.example.gameDemo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/notification")
public class NotificationMasterController
{
    @Autowired
    private NotificationService notificationService;

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody NotificationMaster notificationMaster)
    {
        Boolean flag=notificationService.save(notificationMaster);
        if (flag)
        return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping(value = "/update")
    private ResponseEntity update(@RequestBody NotificationMaster notificationMaster)
    {
        Boolean flag=notificationService.update(notificationMaster);
        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/getAll")
    private ResponseEntity getAll()
    {
       List list=notificationService.getAll();

            return new ResponseEntity(list, HttpStatus.OK);

    }


    @DeleteMapping(value = "/deleteByNotificationMasterId/{notificationMasterId}")
    private ResponseEntity delete(@PathVariable("notificationMasterId")Integer notificationMasterId)
    {
        Boolean flag=notificationService.delete(notificationMasterId);
        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @GetMapping(value = "/getByNotificationMasterId/{notificationMasterId}")
    private ResponseEntity getByNotificationMasterId(@PathVariable("notificationMasterId")Integer notificationMasterId)
    {
        NotificationMaster  notificationMaster=notificationService.getByNotificationMasterId(notificationMasterId);
        if (notificationMaster!=null)
            return new ResponseEntity(notificationMaster, HttpStatus.OK);
        else
            return new ResponseEntity(notificationMaster, HttpStatus.INTERNAL_SERVER_ERROR);



    }

    @GetMapping(value = "/getActiveList")
    public ResponseEntity getActiveNotifications()
    {
        List list = notificationService.getActiveNotifications();
        if(list.size()!=0)
        {
            return new ResponseEntity(list,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
