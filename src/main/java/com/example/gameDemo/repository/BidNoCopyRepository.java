package com.example.gameDemo.repository;

import com.example.gameDemo.model.bidNoCopyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidNoCopyRepository extends JpaRepository<bidNoCopyModel,Integer> {


    boolean existsByUserId(Long userId);

    List findAllByUserId(Long userId);
}
