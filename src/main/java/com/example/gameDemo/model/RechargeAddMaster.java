package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "recharge_add_master")
@Getter
@Setter
@NoArgsConstructor
public class RechargeAddMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rechargeAddId;

    @Column
    private Integer number;


}
