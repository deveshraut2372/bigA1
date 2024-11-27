package com.example.gameDemo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification_master")
@Setter @Getter @NoArgsConstructor
public class NotificationMaster
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationMasterId;

    @Column(length = 200)
    private String notificationMasterTitle;

    @Column(length = 1000)
    private String notificationMasterDesc;

    @Temporal(TemporalType.TIMESTAMP)
    private Date notificationMasterDate;

    @Column(length = 10)
    private String status;

}
