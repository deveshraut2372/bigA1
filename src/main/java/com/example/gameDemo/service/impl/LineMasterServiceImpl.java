package com.example.gameDemo.service.impl;

import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.LineMasterReqDto;
import com.example.gameDemo.payload.res.LineHistoryResDto;
import com.example.gameDemo.payload.res.LineMasterResDto;
import com.example.gameDemo.payload.res.LineStatusResDto;
import com.example.gameDemo.payload.res.ResultLineWiseResDto;
import com.example.gameDemo.repository.LineMasterDao;
import com.example.gameDemo.service.LineMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class LineMasterServiceImpl implements LineMasterService {

    @Autowired
    private LineMasterDao lineMasterDao;

    @Override
    public Boolean createLineMaster(LineMasterReqDto lineMasterReqDto) {
        LineMaster lineMaster=new LineMaster();
        BeanUtils.copyProperties(lineMasterReqDto,lineMaster);
        UserMaster userMaster=new UserMaster();
        userMaster.setId(lineMasterReqDto.getId());
        lineMaster.setUserMaster(userMaster);
        lineMaster.setOpenStatus(true);
        lineMaster.setFinalStatus(true);

        try {
            lineMasterDao.save(lineMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateLineMaster(LineMasterReqDto lineMasterReqDto) {

        System.out.println(" lineMasterReqDto =="+lineMasterReqDto.toString());

        LineMaster lineMaster=new LineMaster();
        BeanUtils.copyProperties(lineMasterReqDto,lineMaster);
        UserMaster userMaster=new UserMaster();
        userMaster.setId(lineMasterReqDto.getId());
        lineMaster.setDailyStatus(true);
        lineMaster.setUserMaster(userMaster);

        try {
            lineMasterDao.save(lineMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getActiveLines(){
        List list=new ArrayList<>();

        List<LineMaster> lineMasterList=lineMasterDao.findAllByDailyStatusAndStatusOrderByLineTimeAsc(true,"Active");
        for(LineMaster lineMaster:lineMasterList){
            LineHistoryResDto lineMasterResDto=new LineHistoryResDto();
            lineMasterResDto.setLineId(lineMaster.getLineId());
            lineMasterResDto.setLineName(lineMaster.getLineName());
            lineMasterResDto.setLineDate(lineMaster.getLineDate());
            lineMasterResDto.setLineOpenNo(lineMaster.getLineOpenNo());
            lineMasterResDto.setLineMidNo(lineMaster.getLineMidNo());
            lineMasterResDto.setLineCloseNo(lineMaster.getLineCloseNo());
            list.add(lineMasterResDto);
        }
        return list;


}

    @Override
    public List getAllAllLines(Long id) {
        List<LineMaster> list=lineMasterDao.findAllByUserMasterOrderByLineDate(id);
        return list;
    }

    @Override
    public LineMasterReqDto editLine(Integer lineId) {
        try {
            LineMaster lineMaster = lineMasterDao.getById(lineId);

            LineMasterReqDto lineMasterReqDto=new LineMasterReqDto();

            BeanUtils.copyProperties(lineMaster,lineMasterReqDto);

            return lineMasterReqDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LineStatusResDto getLineStatus(Integer lineId) {
        try {

            LineStatusResDto lineStatusResDto=lineMasterDao.findByLineId(lineId);
            return lineStatusResDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LineMaster> getAllResultByName(String name) {
        List<LineMaster> list = new ArrayList<LineMaster>();
        try {
            list= (List) lineMasterDao.findAllByLineName(name);
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
            return list;

        }
    }

    @Override
    public List getAllAllLinesList() {
        List<LineMaster> list = new ArrayList<LineMaster>();
        try {
            list= (List) lineMasterDao.findAll();
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
            return list;

        }
    }

    @Override
    public Boolean addLineTime() {
        Boolean flag=false;
        List<LineMaster> lineMasterList=lineMasterDao.findByStatusAndLineTime("Active",null);
        System.out.println("lineList"+lineMasterList);
        for (LineMaster lineMaster:lineMasterList)
        {
            try {
                String pattern = "HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(lineMaster.getLineStartTime());
                Date date1 = new SimpleDateFormat(pattern).parse(date);
                Integer update=lineMasterDao.updateLineTime(date1,lineMaster.getLineId());
                System.out.println("update"+update);
                flag=true;
            }catch (Exception e)
            {
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public List getActiveLinesYear(String name) {
        List<LineMaster> lineMasters = lineMasterDao.findAllByLineName(name);
        List tempList = new ArrayList<>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        String stringDate1 = sdf1.format(lineMasters.get(0).getLineDate());
        tempList.add(stringDate1);

        for (LineMaster lineMaster: lineMasters) {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            String stringDate2 = sdf2.format(lineMaster.getLineDate());
            if(tempList.contains(stringDate2)) {
                System.out.println("contain");
            } else {
                tempList.add(stringDate2);
            }
        }
        System.out.println("tempList...."+tempList.toString());
        return tempList;
    }

    @Override
    public List getResult(String name, String year) {
        System.out.println("  name =="+name);
        System.out.println("  year =="+year);
        List<LineMaster> list1=new ArrayList<LineMaster>();
        List<ResultLineWiseResDto> list = new ArrayList<>();
//        List<ResultLineWiseResDto> resultLineWiseResDtos=new ArrayList<>();
        list1 = lineMasterDao.getLineMasterName(name);
        System.out.println("  list1 =="+list1.size());
        System.out.println("  list1 =="+list1.toString());

        for(LineMaster lineMaster:list1){
            ResultLineWiseResDto resultLineWiseResDto=new ResultLineWiseResDto();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            String stringDate2 = sdf2.format(lineMaster.getLineDate());
            if(stringDate2.equals(year)){
//                list.add(lineMaster);
                Date date=lineMaster.getLineDate();
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMM yyyy", Locale.ENGLISH);
                String formattedDate = sdf.format(date);
                resultLineWiseResDto.setLineCloseNo(lineMaster.getLineCloseNo());
                resultLineWiseResDto.setLineDate(formattedDate);
                resultLineWiseResDto.setLineMidNo(lineMaster.getLineMidNo());
                resultLineWiseResDto.setLineOpenNo(lineMaster.getLineOpenNo());

                list.add(resultLineWiseResDto);
            }
        }
        System.out.println("  Result list ="+list);
        return list;
    }

    @Override
    public List getAllActiveList() {
        List list=new ArrayList<>();

        List<LineMaster> lineMasterList=lineMasterDao.findAllByDailyStatusAndStatusOrderByLineTimeAsc(true,"Active");
        for(LineMaster lineMaster:lineMasterList){
            LineMasterResDto lineMasterResDto=new LineMasterResDto();
            lineMasterResDto.setLineId(lineMaster.getLineId());
            lineMasterResDto.setLineName(lineMaster.getLineName());
            lineMasterResDto.setLineDate(lineMaster.getLineDate());
            lineMasterResDto.setLineOpenNo(lineMaster.getLineOpenNo());
            lineMasterResDto.setLineMidNo(lineMaster.getLineMidNo());
            lineMasterResDto.setLineCloseNo(lineMaster.getLineCloseNo());
            lineMasterResDto.setStatus(lineMaster.getStatus());
            lineMasterResDto.setDailyStatus(lineMaster.getDailyStatus());
            lineMasterResDto.setFinalStatus(lineMaster.getFinalStatus());
            lineMasterResDto.setRedFlag(lineMaster.getRedFlag());
            lineMasterResDto.setLineTime(lineMaster.getLineTime());
            lineMasterResDto.setLineStartTime(lineMaster.getLineStartTime());
            lineMasterResDto.setLineEndTime(lineMaster.getLineEndTime());


            list.add(lineMasterResDto);
        }
        return list;
    }

    @Override
    public List getPendingResultLines() {
        List<LineMaster> lineMasters=new ArrayList<>();
       lineMasters= (List<LineMaster>) lineMasterDao.findAll();
       List list=lineMasters.stream().filter(lineMaster -> lineMaster.getLineCloseNo()==null).collect(Collectors.toList());
        return list;
    }

}
