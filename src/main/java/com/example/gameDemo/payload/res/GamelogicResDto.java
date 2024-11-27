package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter@AllArgsConstructor
public class GamelogicResDto {
    private String bidNo;
    private Double point;
    private String session;
    private String gameRateName;

    public GamelogicResDto() {

    }


}
