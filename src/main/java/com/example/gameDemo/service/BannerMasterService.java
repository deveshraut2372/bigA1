package com.example.gameDemo.service;





import com.example.gameDemo.model.BannerMaster;
import com.example.gameDemo.payload.req.BannerMasterReqDto;

import java.util.List;

public interface BannerMasterService {
    
    Boolean createLBannerMaster(BannerMasterReqDto bannerMasterReqDto);

    Boolean updateBannerMaster(BannerMasterReqDto bannerMasterReqDto);

    List<BannerMaster> getActiveBannerMaster();

    List getAllBannerMasterList();

    BannerMaster editBannerMaster(Integer bannerId);
}
