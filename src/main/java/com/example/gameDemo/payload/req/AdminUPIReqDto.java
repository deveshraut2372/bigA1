package com.example.gameDemo.payload.req;

import com.example.gameDemo.model.UserMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class AdminUPIReqDto {

    private Integer adminUPIId;

    private Long id;

    private String upiId;

    private String status;

    private Date date;

    private String qrCode;

}
