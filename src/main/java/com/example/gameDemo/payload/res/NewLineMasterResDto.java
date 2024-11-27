package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class NewLineMasterResDto {
    private Integer newlineId;
    private String newlineName;
    private Date newlineStartTime;
    private Date newlineEndTime;
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

    public NewLineMasterResDto() {

    }
}
