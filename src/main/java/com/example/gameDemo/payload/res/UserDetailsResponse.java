package com.example.gameDemo.payload.res;

import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsResponse {
    private Long id;
    private String fullName;
    private String mobileNo;
    private String email;
    private String password;
    private String status;
    private Double walletPoints;
    private String googlePayNo;
    private String phonePayNo;
    private String bankAccountNo;
    private String bankName;
    private String ifscCode;
    private String accountHolderName;
    private String paytmNo;

    private String address;
    private String city;
    private String pinCode;

    private Long agentId;
    private String agentFullName;

}

