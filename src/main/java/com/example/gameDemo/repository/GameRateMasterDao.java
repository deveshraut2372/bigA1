package com.example.gameDemo.repository;


import com.example.gameDemo.model.GameRateMaster;
import com.example.gameDemo.payload.res.GameRateMasterResDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface GameRateMasterDao extends CrudRepository<GameRateMaster,Integer> {
    @Query("select new com.example.gameDemo.payload.res.GameRateMasterResDto(grm.gameRateMasterId,grm.gameName,grm.gameRate,grm.status) from GameRateMaster as grm")
    List getAllList();

    @Query("select new com.example.gameDemo.payload.res.GameRateMasterResDto(cm.gameRateMasterId,cm.gameName,cm.gameRate,cm.status)from GameRateMaster as cm where cm.gameRateMasterId=:gameRateMasterId")
    Optional<GameRateMasterResDto> getByGameRateMasterId(@Param("gameRateMasterId") Integer gameRateMasterId);

//    @Query("select new com.example.gameDemo.payload.res.GameRateMasterResDto(cm.gameRateMasterId,cm.gameName,cm.gameRate,cm.status,cm.userMaster.id,cm.userMaster.username) from GameRateMaster as cm where  cm.status='Active'")
//    List getGameRateListByUserId(@Param("id") Long id);

//    @Query("select gr.gameRate as gameRate from GameRateMaster as gr where gr.gameName=:gameName and gr.userMaster.id=:id")
//    Double getGameRateByName(@Param("gameName") String gameName, @Param("id") Long id);

    @Query("select new com.example.gameDemo.payload.res.GameRateMasterResDto(cm.gameRateMasterId,cm.gameName,cm.gameRate,cm.status)from GameRateMaster as cm where cm.status='Active'")
    List<GameRateMasterResDto> getActiveList();

    @Query("select gm from GameRateMaster as gm where gm.gameRateMasterId=:gameRateMasterId")
    GameRateMaster getByGameRateMasterIdWise(@Param("gameRateMasterId") Integer gameRateMasterId);

//    @Query("select nm from GameRateMaster as nm  where  nm.userMaster.id=:id")
//    GameRateMaster updateUserIdWiseGameRate(@Param("id") Long id);
}
