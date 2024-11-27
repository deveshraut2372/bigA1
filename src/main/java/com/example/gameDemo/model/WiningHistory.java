package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "wining_history")
@Getter @Setter @NoArgsConstructor@ToString
public class WiningHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer winingId;

    private Double winPoints;

    @Column(length = 100)
    private String winStatus;

    @Column
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date winDate;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 30)
    private String value;

    @Column(length = 30)
    private String session;

    private Double bidPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserMaster userMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id")
    private LineMaster lineMaster;

}
