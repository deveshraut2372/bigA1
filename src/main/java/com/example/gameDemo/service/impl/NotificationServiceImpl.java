package com.example.gameDemo.service.impl;


import com.example.gameDemo.model.NotificationMaster;
import com.example.gameDemo.repository.NotificationDao;
import com.example.gameDemo.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public Boolean save(NotificationMaster notificationMaster)
    {
        try
        {
            notificationMaster.setNotificationMasterDate(new Date());
            notificationDao.save(notificationMaster);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean update(NotificationMaster notificationMaster) {

        try
        {
            notificationDao.save(notificationMaster);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getAll() {
        return (List) notificationDao.findAll();
    }

    @Override
    public Boolean delete(Integer notificationMasterId) {
        try {

            notificationDao.deleteById(notificationMasterId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NotificationMaster getByNotificationMasterId(Integer notificationMasterId) {
        NotificationMaster notificationMaster=new NotificationMaster();
        try {
            Optional<NotificationMaster> notificationMaster1=notificationDao.findById(notificationMasterId);
            notificationMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, notificationMaster));
            return notificationMaster;
        }
        catch (Exception e) {
            e.printStackTrace();
            return notificationMaster;
        }    }
    @Override
    public List getActiveNotifications() {
        return notificationDao.findAllByStatus("Active");
    }
}
