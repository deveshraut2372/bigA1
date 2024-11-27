package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class OtpResDto {
    private Boolean flag;
    private Integer otp;
    private String mobNo;

    public OtpResDto() {

    }
}
