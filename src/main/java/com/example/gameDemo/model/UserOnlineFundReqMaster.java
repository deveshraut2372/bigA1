package com.example.gameDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_Online_Fund_Req_Master")
public class UserOnlineFundReqMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userOnlineFundId;

//    @Column
//    private Double points;

    @Column
    private String status;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private String paymentScreenShort;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserMaster userMaster;


}
