package com.example.gameDemo.payload.req;

import com.example.gameDemo.model.UserMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class UserOnlineFundReq {

    private Integer userOnlineFundId;

//    private Double points;

    private String status;

    private Date date;

    private String paymentScreenShort;

    private Long id;

}
