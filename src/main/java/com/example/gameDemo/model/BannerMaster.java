package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "banner_master")
@Getter
@Setter
@NoArgsConstructor
public class BannerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bannerId;


    private LocalDate expiryDate;

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String type;

    @Column(length = 500)
    private String path;

    @Column(length = 50)
    private String status;

}
