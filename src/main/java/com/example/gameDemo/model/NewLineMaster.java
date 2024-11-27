package com.example.gameDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new_line_master")
@Getter @Setter @NoArgsConstructor@ToString
public class NewLineMaster
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newlineId;

    @Column(length = 100)
    private String newlineName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date newlineStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date newlineEndTime;

    @Temporal(TemporalType.TIME)
    private Date lineTime;

    @Column
    private String status;

    private Boolean monday;

    private Boolean tuesday;

    private Boolean wednesday;

    private Boolean thursday;

    private Boolean friday;

    private Boolean saturday;

    private Boolean sunday;

    private Integer lineOpenNo;

    private String lineMidNo;


    private Integer lineCloseNo;



}
