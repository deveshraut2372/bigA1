package com.example.gameDemo.repository;


import com.example.gameDemo.model.NotificationMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationDao extends JpaRepository<NotificationMaster,Integer> {

    List findAllByStatus(String active);
}
