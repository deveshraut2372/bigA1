package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;

@Getter@Setter@AllArgsConstructor
public class GameRateUserWiseResDto {
    private Integer gameRateUserMasterId;
    private String gameName;
    private Double gameRate;
    private String status;
    private Long id;
    private Integer gameRateMasterId;

    public GameRateUserWiseResDto() {

    }
}
