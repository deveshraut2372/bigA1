package com.example.gameDemo.payload.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class DateReqDto {
    private Date bidDate=new Date();
}
