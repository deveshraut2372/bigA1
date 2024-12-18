package com.example.gameDemo.repository;


import com.example.gameDemo.model.BannerMaster;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BannerMasterDao extends CrudRepository<BannerMaster,Integer> {

    List<BannerMaster> findAllByStatus(String active);

}
