package com.example.gameDemo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game_rate_user_master")
@Getter
@Setter
@NoArgsConstructor
public class GameRateUserWiseMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameRateUserMasterId;

    @Column(length = 200)
    private String gameName;

    private Double gameRate;

    @Column(length = 200)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserMaster userMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_rate_master_id")
    private GameRateMaster gameRateMaster;




}
