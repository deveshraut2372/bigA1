package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class AgentFundResDto {
    private String agentName;
    private String agentMobNo;
    private Double agentwalletPoints;
    private Double agentRequestPoints;
    private String agentStatus;
    private Date agentDateTime;
    private String agentFundType;
    public AgentFundResDto(String agentName, String agentMobNo, Double agentwalletPoints, Double agentRequestPoints, String agentStatus,Date agentDateTime) {
        this.agentName = agentName;
        this.agentMobNo = agentMobNo;
        this.agentwalletPoints = agentwalletPoints;
        this.agentRequestPoints = agentRequestPoints;
        this.agentStatus = agentStatus;
        this.agentDateTime=agentDateTime;
    }

    public AgentFundResDto(String agentName, String agentMobNo, Double agentwalletPoints, Double agentRequestPoints, String agentStatus,Date agentDateTime,String agentFundType) {
        this.agentName = agentName;
        this.agentMobNo = agentMobNo;
        this.agentwalletPoints = agentwalletPoints;
        this.agentRequestPoints = agentRequestPoints;
        this.agentStatus = agentStatus;
        this.agentDateTime=agentDateTime;
        this.agentFundType=agentFundType;
    }

    public AgentFundResDto() {

    }
}
