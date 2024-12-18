package com.example.gameDemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mobileNo")
        })
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    private String fullName;
    private String status;
    private String googlePayNo;
    private String phonePayNo;
    private Double walletPoints=0.0;
    private  Integer RegisterOTP;

    @Column
    private String whatsAppNo;


    private String address;


    @Column
    private String email;

    @Column
    private String adharcard;

    @Column
    private String pancard;

    @Column
    private String photo;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String mobileNo;

    private String token;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column
    private String canPlay;

    @Column
    private String fcmTokenString;

    @Column
    private Integer otp;


    @Column
    private Boolean canPlayFlag;

    @Column
    private Integer commision;

    @Column
    private String sPassword;

    private Long agentId;

    @Column
    private String bankAccountNo;

    @Column
    private String bankName;

    @Column
    private String ifscCode;

    @Column
    private String accountHolderName;

    @Column
    private String paytmNo;

    @Column
    private String city;

    @Column
    private String pinCode;
}
