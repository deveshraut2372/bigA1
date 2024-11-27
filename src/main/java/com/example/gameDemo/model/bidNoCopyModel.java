package com.example.gameDemo.model;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.example.gameDemo.payload.res.TransationResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bidNoCopyModel")
public class bidNoCopyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CopyBidNoId;
    private String tranStatus;
    private String tranType;
    private Long userId;
    private String lineName;
    private String session;
    private String uniqueNo;
    private Double totalPoint;
    private String bidNo;
    private Double points;
    private String balance;





}
