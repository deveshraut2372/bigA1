package com.example.gameDemo.service.impl;



import com.example.gameDemo.model.BannerMaster;
import com.example.gameDemo.payload.req.BannerMasterReqDto;
import com.example.gameDemo.repository.BannerMasterDao;
import com.example.gameDemo.service.BannerMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannerMasterServiceImpl implements BannerMasterService {

    @Autowired
    private BannerMasterDao bannerMasterDao;

    @Override
    public Boolean createLBannerMaster(BannerMasterReqDto bannerMasterReqDto) {

        BannerMaster bannerMaster=new BannerMaster();

        BeanUtils.copyProperties(bannerMasterReqDto,bannerMaster);
        bannerMaster.setStatus("Active");

        try {
            bannerMasterDao.save(bannerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateBannerMaster(BannerMasterReqDto bannerMasterReqDto) {

        BannerMaster bannerMaster=new BannerMaster();

        BeanUtils.copyProperties(bannerMasterReqDto,bannerMaster);

        try {
            bannerMasterDao.save(bannerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BannerMaster getActiveBannerMaster() {

        BannerMaster list=bannerMasterDao.findAllByStatus("Active");
        return list;
    }

    @Override
    public List getAllBannerMasterList() {
        return (List) bannerMasterDao.findAll();
    }

    @Override
    public BannerMaster editBannerMaster(Integer bannerId) {

        BannerMaster bannerMaster=new BannerMaster();
        try {
            Optional<BannerMaster> bannerMaster1=bannerMasterDao.findById(bannerId);
            bannerMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, bannerMaster));
            return bannerMaster;
        }
        catch (Exception e) {
            e.printStackTrace();
            return bannerMaster;
        }    }

}
