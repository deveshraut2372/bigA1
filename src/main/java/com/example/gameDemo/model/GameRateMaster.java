package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "game_rate_master")
@Getter
@Setter
@NoArgsConstructor@ToString
public class GameRateMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameRateMasterId;

    @Column(length = 200)
    private String gameName;

    private Double gameRate;

    @Column(length = 200)
    private String status;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    private UserMaster userMaster;
}
