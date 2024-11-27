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
    private String email;
    private String password;
    private String mobileNo;
    private String status;
    private String role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String adharcard;
    private String pancard;
    private String photo;
    private String canPlay;
    private String googlePayNo;
    private String phonePayNo;
    private String username;

    private Date date;

    private String whatsAppNo;


    private String address;



}
