package com.example.gameDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Admin_UPIID_MAster")
public class AdminUPIMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminUPIId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserMaster userMaster;

    @Column
    private String upiId;

    @Column
    private String status;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private String qrCode;


}
