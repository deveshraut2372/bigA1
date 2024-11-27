package com.example.gameDemo.service;

import com.example.gameDemo.payload.req.DelectResultReqDto;
import com.example.gameDemo.payload.req.ResultCalReqDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ResultService {
    Boolean openResultCal(ResultCalReqDto resultCalReqDto);

    Boolean clseResultCal(ResultCalReqDto resultCalReqDto);

    Boolean openResultDebCal(ResultCalReqDto resultCalReqDto);

    Boolean clseResultDebCal(ResultCalReqDto resultCalReqDto);



    Boolean deleteResult(DelectResultReqDto delectResultReqDto);
}
