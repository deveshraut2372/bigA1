package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.bidNoCopyModel;
import com.example.gameDemo.payload.req.bidNoCopyModelReq;
import com.example.gameDemo.payload.res.BidNoCopyRes;
import com.example.gameDemo.payload.res.GamelogicResDto;
import com.example.gameDemo.payload.res.TransationResDto;
import com.example.gameDemo.payload.res.UserTranstionResDto;
import com.example.gameDemo.repository.BidNoCopyRepository;
import com.example.gameDemo.service.BidNoCopyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidNoCopyServiceImpl implements BidNoCopyService {

    @Autowired
    private BidNoCopyRepository bidNoCopyRepository;

    @Override
    public Boolean copyBidNo(bidNoCopyModelReq bidNoCopyModelReq) {

            if (bidNoCopyRepository.existsByUserId(bidNoCopyModelReq.getUserId())) {
                List list = new ArrayList<>();
                list = bidNoCopyRepository.findAllByUserId(bidNoCopyModelReq.getUserId());
                bidNoCopyRepository.deleteAll(list);
                bidNoCopyModel bidNoCopyModel = new bidNoCopyModel();
                bidNoCopyModel.setTranStatus(bidNoCopyModelReq.getTranStatus());
                bidNoCopyModel.setTranType(bidNoCopyModelReq.getTranType());
                bidNoCopyModel.setUserId(bidNoCopyModelReq.getUserId());
                bidNoCopyModel.setLineName(bidNoCopyModelReq.getLineName());
                bidNoCopyModel.setSession(bidNoCopyModelReq.getSession());
                bidNoCopyModel.setUniqueNo(bidNoCopyModelReq.getUniqueNo());
                for (TransationResDto transationResDto : bidNoCopyModelReq.getTransationResDtos()) {
                    bidNoCopyModel bidNoCopyModel1=new bidNoCopyModel();
                    BeanUtils.copyProperties(bidNoCopyModel,bidNoCopyModel1);
                    bidNoCopyModel1.setBidNo(transationResDto.getValue());
                    bidNoCopyModel1.setPoints(transationResDto.getPoints());
                    bidNoCopyRepository.save(bidNoCopyModel1);
                    System.out.println("  bidNoCopyModel1 =="+bidNoCopyModel1.getBidNo()+"  "+bidNoCopyModel1.toString());
                }
                System.out.println("  hii");
            } else {
                bidNoCopyModel bidNoCopyModel = new bidNoCopyModel();
                bidNoCopyModel.setTranStatus(bidNoCopyModelReq.getTranStatus());
                bidNoCopyModel.setTranType(bidNoCopyModelReq.getTranType());
                bidNoCopyModel.setUserId(bidNoCopyModelReq.getUserId());
                bidNoCopyModel.setLineName(bidNoCopyModelReq.getLineName());
                bidNoCopyModel.setSession(bidNoCopyModelReq.getSession());
                bidNoCopyModel.setUniqueNo(bidNoCopyModelReq.getUniqueNo());
                for (TransationResDto transationResDto : bidNoCopyModelReq.getTransationResDtos()) {
                    bidNoCopyModel bidNoCopyModel1=new bidNoCopyModel();
                    BeanUtils.copyProperties(bidNoCopyModel,bidNoCopyModel1);
                    bidNoCopyModel1.setBidNo(transationResDto.getValue());
                    bidNoCopyModel1.setPoints(transationResDto.getPoints());
                    bidNoCopyRepository.save(bidNoCopyModel1);
                }
            }
            return true;

    }

    @Override
    public List<GamelogicResDto>  getCopyBidNo(Long userId) {
        List<GamelogicResDto> publiclist = new ArrayList<>();

        List<bidNoCopyModel> bidNoCopyModelList=new ArrayList<>();
        bidNoCopyModelList=bidNoCopyRepository.findAllByUserId(userId);

        if(!bidNoCopyModelList.isEmpty())
        {
            for (bidNoCopyModel bidNoCopyModel : bidNoCopyModelList) {
                GamelogicResDto gamelogicResDto=new GamelogicResDto();
                gamelogicResDto.setPoint(bidNoCopyModel.getPoints());
                gamelogicResDto.setBidNo(bidNoCopyModel.getBidNo());
                gamelogicResDto.setSession(bidNoCopyModel.getSession());
                gamelogicResDto.setGameRateName("");
                publiclist.add(gamelogicResDto);
            }
        }


        return publiclist;
    }


}
