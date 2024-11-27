package com.example.gameDemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_transction_history")
@Getter @Setter @NoArgsConstructor
public class UserTransctionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userTransctionId;

    private Double points;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;


    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 30)
    private String tranStatus;

    @Column(length = 30)
    private String tranType;

    private String lineName;

    private String balance;

    @Column
    private String session;

    @Column
    private String uniqueNo;

    private String value;

    private String paymentType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserMaster userMaster;

//    @Column
//    private Double
}
