package com.example.gameDemo.payload.res;

import com.example.gameDemo.model.UserMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUPIRes {

    private Integer adminUPIId;

    private Long id;

    private String upiId;

    private String status;

    private Date date;

    private String qrCode;

}
