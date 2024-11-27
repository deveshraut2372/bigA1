package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "fund_master")
@Getter
@Setter
@NoArgsConstructor
public class FoundMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fundRequestId;

    private Double fundPoints;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(length = 50)
    private String fundType;

    @Column(length = 50)
    private String status;

    @Column
    private String paymentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserMaster userMaster;

    @Column(length = 200)
    private String reason;


}
