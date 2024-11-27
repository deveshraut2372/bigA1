package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter@Setter
public class BannerMasterReqDto {

    private Integer bannerId;
    private LocalDate expiryDate;
    private String title;
    private String type;
    private String path;
    private String status;
}
