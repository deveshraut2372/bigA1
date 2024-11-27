package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class LineStatusResDto {

    private Boolean openStatus;

    private Boolean finalStatus;
}
