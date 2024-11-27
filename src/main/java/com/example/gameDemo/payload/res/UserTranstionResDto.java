package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
@Data
@Getter@Setter@AllArgsConstructor
public class UserTranstionResDto {
//    private Integer userTransctionId;

    private Date dateTime;
    private String tranStatus;
    private String tranType;
//    private Long id;
    private String lineName;
    private String session;
    private String uniqueNo;
    private Double totalPoint;
    private String username;
    private String mobileNo;

    List<TransationResDto> transationResDtos;

    private String balance;

    public UserTranstionResDto() {

    }


}
