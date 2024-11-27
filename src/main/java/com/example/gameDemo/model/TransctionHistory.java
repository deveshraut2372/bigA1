package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transction_history")
@Getter
@Setter
@NoArgsConstructor
public class TransctionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transctionId;

    private Double points;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;

    @Column(length = 30)
    private String tranStatus;

    @Column(length = 30)
    private String tranType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserMaster userMaster;

    @Column
    private String balance;

}
