package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOnlineFundRes {

    private Integer userOnlineFundId;

    private String status;

    private Date date;

    private String paymentScreenShort;

    private String username;

    private String mobileNo;

    private Long id;
//    private UserMaster userMaster;
}



