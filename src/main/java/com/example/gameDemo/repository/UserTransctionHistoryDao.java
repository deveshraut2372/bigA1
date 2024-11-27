package com.example.gameDemo.repository;

import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.model.UserTransctionHistory;
import com.example.gameDemo.payload.res.TransationResDto;
import com.example.gameDemo.payload.res.UserTranstionResDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserTransctionHistoryDao extends CrudRepository<UserTransctionHistory,Integer> {
    @Query("select um from UserTransctionHistory as um where um.userMaster.id=:id  ORDER BY um.userTransctionId DESC")
    List<UserTransctionHistory> getByID(@Param("id") Long id);



    @Query("select new com.example.gameDemo.payload.res.TransationResDto(um.value,um.points) from UserTransctionHistory as um where um.uniqueNo=:uniqueNo ORDER BY (um.dateTime) DESC ")
    List<TransationResDto> getValue(@Param("uniqueNo") String uniqueNo);

    @Query("select um from UserTransctionHistory as um where um.date=:bidDate")
    List<UserTransctionHistory> getByDAte(@Param("bidDate") Date bidDate);

    @Query("select sum(m.points) FROM UserTransctionHistory m where m.date=:bidDate and m.tranStatus='Debit'")
    Double getSumOfBidPoints(@Param("bidDate")Date bidDate);

    @Query("select sum(m.points) FROM UserTransctionHistory m where m.date=:bidDate and m.tranStatus='Credit'")
    Double getSumOfBidPoint(@Param("bidDate")Date bidDate);

    @Query("select m FROM UserTransctionHistory m where m.date=:bidDate ")
    List<UserTransctionHistory> getAllByBidDate(@Param("bidDate") Date bidDate);


//@Query("select new com.example.gameDemo.payload.res.UserTranstionResDto(um.userTransctionId,um.points,um.dateTime,um.tranStatus,um.tranType,um.userMaster.id,um.lineName,um.session) from UserTransctionHistory as um where um.userMaster.id=:id")
//    List<UserTranstionResDto> getuserTransctionList(@Param("id") Long id);
}
