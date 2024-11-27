package com.example.gameDemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "bid_master")
@Getter
@Setter

public class BidHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bidId;

    @Temporal(TemporalType.DATE)
    private Date bidDate;

    @Column(length = 50)
    private String game;

    private Double bidPoint;

    @Column(length = 30)
    private String value;

    @Column(length = 100)
    private String session;

    @Column(length = 50)
    private String status;

    private String gameRateName;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserMaster userMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id")
    private LineMaster lineMaster;
}
