package com.example.gameDemo.service;



import com.example.gameDemo.model.NewLineMaster;
import com.example.gameDemo.payload.res.NewLineMasterResDto;

import java.text.ParseException;
import java.util.List;

public interface NewLineMasterService {
    Boolean createNewlineMaster(NewLineMaster newLineMaster) throws ParseException;

    Boolean updateNewLineMaster(NewLineMaster newLineMaster) throws ParseException;

    List getActiveListOfNewLineMaster();

    NewLineMasterResDto editNewLine(Integer newlineId);

    Boolean deleteByNewLineId(Integer newlineId);

    Boolean scheduleLineMaster();

    List getAllNewLineMaster();

    List getDayWiseData();


    List getResultNewLine();

    Boolean addLineTime() throws ParseException;
}
