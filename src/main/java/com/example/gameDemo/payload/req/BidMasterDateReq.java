package com.example.gameDemo.payload.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class BidMasterDateReq {
    private Date bidDate;
}
