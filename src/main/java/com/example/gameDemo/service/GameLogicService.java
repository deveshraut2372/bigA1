package com.example.gameDemo.service;

import com.example.gameDemo.payload.req.GameLogicReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameLogicService {

    List allGameList(GameLogicReq gameLogicReq);

    List removeObject(GameLogicReq gameLogicReq);
}
