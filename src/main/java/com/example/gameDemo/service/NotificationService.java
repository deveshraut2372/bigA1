package com.example.gameDemo.service;



import com.example.gameDemo.model.NotificationMaster;

import java.util.List;

public interface NotificationService {
    Boolean save(NotificationMaster notificationMaster);

    Boolean update(NotificationMaster notificationMaster);

    List getAll();

    Boolean delete(Integer notificationMasterId);

    NotificationMaster getByNotificationMasterId(Integer notificationMasterId);

    List getActiveNotifications();
}
