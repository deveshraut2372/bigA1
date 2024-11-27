package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "line_master")
@Getter @Setter @NoArgsConstructor@ToString
public class LineMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lineId;

    @Column(length = 100)
    private String lineName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lineStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lineEndTime;

    @Temporal(TemporalType.DATE)
    private Date lineDate;

    @Column(length = 3)
    private Integer lineOpenNo;

    @Temporal(TemporalType.TIME)
    private Date lineTime;

    @Column(length = 2)
    private String lineMidNo;

    @Column(length = 3)
    private Integer lineCloseNo;

    @Column(length = 10)
    private String status;

    @Column
    private Boolean redFlag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserMaster userMaster;

    private Boolean openStatus;

    private Boolean finalStatus;

    private Boolean dailyStatus;


    @Transient
    private Boolean timeFlag;



}
