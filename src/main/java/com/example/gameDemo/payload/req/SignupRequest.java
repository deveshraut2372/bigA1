package com.example.gameDemo.payload.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private Long id;
    private Long agentId;
    private String email;
    private String password;
    private String mobileNo;
    private String status;
    private String role;
    private String fullName;
    private String adharcard;
    private String pancard;
    private String photo;
    private String canPlay;
    private String googlePayNo;
    private String phonePayNo;
    private String paytmNo;
    private String username;

    private String address;
    private String city;
    private String pinCode;

    private Date date;

    private String whatsAppNo;

    private String bankAccountNo;
    private String bankName;
    private String ifscCode;
    private String accountHolderName;

}
