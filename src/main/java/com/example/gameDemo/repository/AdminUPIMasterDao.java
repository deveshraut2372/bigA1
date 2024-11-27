package com.example.gameDemo.repository;


import com.example.gameDemo.model.AdminUPIMaster;
import com.example.gameDemo.payload.res.AdminUPIRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminUPIMasterDao extends JpaRepository<AdminUPIMaster,Integer> {


    @Query("select new com.example.gameDemo.payload.res.AdminUPIRes(am.adminUPIId,am.userMaster.id,am.upiId,am.status,am.date,am.qrCode) from AdminUPIMaster as am where am.status='Active'")
    AdminUPIRes getByStatus(String active);
}
