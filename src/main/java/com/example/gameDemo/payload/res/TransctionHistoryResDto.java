package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter@Setter@AllArgsConstructor
public class TransctionHistoryResDto {
    private Integer transctionId;
    private Double points;
    private Date dateAndTime;
    private String tranStatus;
    private String tranType;
    private String username;
    private String mobileNo;

    public TransctionHistoryResDto() {

    }
}
