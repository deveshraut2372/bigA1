package com.example.gameDemo.service;



import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.payload.req.LineMasterReqDto;
import com.example.gameDemo.payload.res.LineStatusResDto;

import java.util.List;

public interface LineMasterService {
    Boolean createLineMaster(LineMasterReqDto lineMasterReqDto);

    Boolean updateLineMaster(LineMasterReqDto lineMasterReqDto);

    List getActiveLines() ;

    List getAllAllLines(Long id);

    LineMasterReqDto editLine(Integer lineId);

    LineStatusResDto getLineStatus(Integer lineId);

    List<LineMaster> getAllResultByName(String name);

    List getAllAllLinesList();

    Boolean addLineTime();

    List getActiveLinesYear(String name);

    List getResult(String name, String year);

    List getAllActiveList();

    List getPendingResultLines();
}
