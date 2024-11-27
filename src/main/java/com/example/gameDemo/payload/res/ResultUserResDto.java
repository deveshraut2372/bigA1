package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@AllArgsConstructor@ToString
public class ResultUserResDto {
    private Long id;

    private String username;

    private String mobileNo;

    private Double bidPoint;

    private String token;

    private String value;
    private String session;



    public ResultUserResDto(Long id, String username, String mobileNo, Double bidPoint, String value, String session) {
        this.id = id;
        this.username = username;
        this.mobileNo = mobileNo;
        this.bidPoint = bidPoint;
        this.value = value;
        this.session = session;
    }
}
