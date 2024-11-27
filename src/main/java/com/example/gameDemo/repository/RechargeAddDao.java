package com.example.gameDemo.repository;

import com.example.gameDemo.model.NotificationMaster;
import com.example.gameDemo.model.RechargeAddMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeAddDao  extends JpaRepository<RechargeAddMaster,Integer> {
    boolean existsByNumber(Integer number);
}
