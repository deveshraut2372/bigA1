package com.example.gameDemo.repository;

import com.example.gameDemo.model.GameRateMaster;
import com.example.gameDemo.model.GameRateUserWiseMaster;
import com.example.gameDemo.payload.res.GameRateUserWiseResDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface GameRateUserDao extends CrudRepository<GameRateUserWiseMaster,Integer> {
    @Query("select gr.gameRate as gameRate from GameRateUserWiseMaster as gr where gr.gameName=:gameName and gr.userMaster.id=:id")
    Double getGameRateByName(@Param("gameName") String gameName,@Param("id") Long id);

    @Query("select new com.example.gameDemo.payload.res.GameRateUserWiseResDto(gm.gameRateUserMasterId,gm.gameName,gm.gameRate,gm.status,gm.userMaster.id,gm.gameRateMaster.gameRateMasterId) from GameRateUserWiseMaster as gm where gm.status='Active' and gm.userMaster.id=:id")
    List<GameRateUserWiseResDto> getActiveListGameRateUser(@Param("id") Long id);

    @Query("select gm from GameRateUserWiseMaster as gm where gm.userMaster.id=:id and gm.gameRateMaster.gameRateMasterId=:gameRateMasterId")
    GameRateUserWiseMaster updateUserIdWiseGameRate(@Param("id") Long id,@Param("gameRateMasterId") Integer gameRateMasterId);

    @Query("select new com.example.gameDemo.payload.res.GameRateUserWiseResDto(gm.gameRateUserMasterId,gm.gameName,gm.gameRate,gm.status,gm.userMaster.id,gm.gameRateMaster.gameRateMasterId) from GameRateUserWiseMaster as gm where gm.gameRateUserMasterId=:gameRateUserMasterId")
    GameRateUserWiseResDto getGameRateUserMasterIdWise(@Param("gameRateUserMasterId") Integer gameRateUserMasterId);
}
