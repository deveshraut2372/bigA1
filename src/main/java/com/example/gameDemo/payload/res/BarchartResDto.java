package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class BarchartResDto {

    List<Long> winingPointList=new ArrayList<>();
    List<Long> bidingPointList=new ArrayList<>();
}
