package com.example.gameDemo.service.impl;



import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.model.NewLineMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.res.NewLineMasterResDto;
import com.example.gameDemo.repository.LineMasterDao;
import com.example.gameDemo.repository.NewLineMasterDao;
import com.example.gameDemo.service.NewLineMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NewLineMasterServiceImpl implements NewLineMasterService {

    @Autowired
    private NewLineMasterDao newLineMasterDao;

    @Autowired
    private LineMasterDao lineMasterDao;


    @Override
    public Boolean createNewlineMaster(NewLineMaster newLineMaster) throws ParseException {
        Boolean flag = false;
        NewLineMaster newLineMaster1 = new NewLineMaster();

        newLineMaster1.setNewlineName(newLineMaster.getNewlineName());
        newLineMaster1.setNewlineStartTime(newLineMaster.getNewlineStartTime());


        newLineMaster1.setNewlineEndTime(newLineMaster.getNewlineEndTime());
        BeanUtils.copyProperties(newLineMaster, newLineMaster1);
        String pattern = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(newLineMaster.getNewlineStartTime());
        Date date1=new SimpleDateFormat(pattern).parse(date);

        newLineMaster1.setLineTime(date1);
        newLineMaster1.setStatus("Active");


        try {
            newLineMasterDao.save(newLineMaster1);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        if (flag) {
            LineMaster lineMaster = new LineMaster();
            SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
            String stringDate2 = sdf2.format(new Date());
            switch (stringDate2) {
                case "Monday":
                    if(newLineMaster.getMonday().equals(true)){
                        lineMaster.setDailyStatus(true);
                    }else if(newLineMaster.getMonday().equals(false)){
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                case "Tuesday":
                    if(newLineMaster.getTuesday().equals(true)){
                        lineMaster.setDailyStatus(true);
                    }else if(newLineMaster.getTuesday().equals(false)){
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                case "Wednesday":
                    if (newLineMaster.getWednesday().equals(true)) {
                        lineMaster.setDailyStatus(true);
                    } else if (newLineMaster.getWednesday().equals(false)) {
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                case "Thursday":
                    if(newLineMaster.getThursday().equals(true)){
                        lineMaster.setDailyStatus(true);
                    }else if(newLineMaster.getThursday().equals(false)){
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                case "Friday":
                    if(newLineMaster.getFriday().equals(true)){
                        lineMaster.setDailyStatus(true);
                    }else if(newLineMaster.getFriday().equals(false)){
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                case "Saturday":
                    if(newLineMaster.getSaturday().equals(true)){
                        lineMaster.setDailyStatus(true);
                    }else if(newLineMaster.getSaturday().equals(false)){
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                case "Sunday":
                    if(newLineMaster.getSunday().equals(true)){
                        lineMaster.setDailyStatus(true);
                    }else if(newLineMaster.getSunday().equals(false)){
                        lineMaster.setDailyStatus(false);
                    }
                    break;
                default:
                    System.out.println("aa........");
            }


            UserMaster userMaster = new UserMaster();
            userMaster.setId(1L);
            lineMaster.setUserMaster(userMaster);
            lineMaster.setLineDate(new Date());
            lineMaster.setLineName(newLineMaster.getNewlineName());
            lineMaster.setLineStartTime(newLineMaster.getNewlineStartTime());
            lineMaster.setLineEndTime(newLineMaster.getNewlineEndTime());
            lineMaster.setLineTime(newLineMaster1.getLineTime());
            lineMaster.setStatus("Active");


            try {
                lineMasterDao.save(lineMaster);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;

    }

    @Override
    public Boolean updateNewLineMaster(NewLineMaster newLineMaster) throws ParseException {

        List<LineMaster> lineMasterList= (List<LineMaster>) lineMasterDao.findAll();

        Boolean flag = false;
        NewLineMaster newLineMaster1 = new NewLineMaster();


        newLineMaster1.setNewlineId(newLineMaster.getNewlineId());
        newLineMaster1.setNewlineName(newLineMaster.getNewlineName());
        newLineMaster1.setNewlineStartTime(newLineMaster.getNewlineStartTime());
        newLineMaster1.setNewlineEndTime(newLineMaster.getNewlineEndTime());
//        newLineMaster1.setLineTime(newLineMaster.getLineTime());
        BeanUtils.copyProperties(newLineMaster, newLineMaster1);

        String pattern = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(newLineMaster.getNewlineStartTime());
        Date date1=new SimpleDateFormat(pattern).parse(date);

        newLineMaster1.setLineTime(date1);
        try {
            newLineMasterDao.save(newLineMaster1);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        for(LineMaster lineMaster:lineMasterList) {
            if(lineMaster.getLineName().equalsIgnoreCase(newLineMaster1.getNewlineName())) {
                lineMaster.setLineTime(newLineMaster.getLineTime());
                lineMaster.setLineStartTime(newLineMaster.getNewlineStartTime());
                lineMaster.setLineEndTime(newLineMaster.getNewlineEndTime());
                lineMaster.setLineTime(newLineMaster1.getLineTime());

                lineMasterDao.save(lineMaster);
            }
        }
        return flag;

    }

    @Override
    public List getActiveListOfNewLineMaster() {
        return newLineMasterDao.findAllByStatusOrderByLineTimeAsc("Active");
    }

    @Override
    public NewLineMasterResDto editNewLine(Integer newlineId) {
        NewLineMasterResDto newLineMasterResDto=newLineMasterDao.getByID(newlineId);
        return newLineMasterResDto;

    }

    @Override
    public Boolean deleteByNewLineId(Integer newlineId) {

        try {
            newLineMasterDao.deleteById(newlineId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean scheduleLineMaster() {
        Boolean flag = false;
        List<NewLineMaster> newLineMasterActiveList = newLineMasterDao.findAllByStatus("Active");
        try {
            Date todaysdate = new Date();
            System.out.println("todays date" + todaysdate);//todays date
            Calendar c = Calendar.getInstance();
            c.setTime(todaysdate);
            c.add(Calendar.DATE, -1);
            todaysdate = c.getTime();
            System.out.println("before date" + todaysdate);

//            Integer updatevalue = lineMasterDao.updateStatusToInActive(todaysdate);
            System.out.println("today date................" + todaysdate);

            for (NewLineMaster newLineMaster : newLineMasterActiveList) {

                LineMaster lineMaster = new LineMaster();

                UserMaster userMaster = new UserMaster();
                userMaster.setId(1L);
                lineMaster.setUserMaster(userMaster);
                lineMaster.setLineDate(new Date());
                lineMaster.setLineName(newLineMaster.getNewlineName());
                lineMaster.setLineStartTime(newLineMaster.getNewlineStartTime());
                lineMaster.setLineEndTime(newLineMaster.getNewlineEndTime());
                lineMaster.setStatus("Inactive");
                lineMaster.setOpenStatus(true);
                lineMaster.setFinalStatus(true);

                try {
                    lineMasterDao.save(lineMaster);
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List getAllNewLineMaster() {

        return (List) newLineMasterDao.findAll();
    }

    @Override
    public List getDayWiseData() {
        List<NewLineMaster> list = new ArrayList();
        List<LineMaster> lineMaster2=lineMasterDao.getLineMaster();
        System.out.println(lineMaster2.toString()+"lineMaster Object");

        Boolean flag = true;
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        String stringDate2 = sdf2.format(new Date());
//        System.out.println("Today is: " + stringDate2);
        stringDate2 = stringDate2.toLowerCase();
//        System.out.println("dff=" + stringDate2);

        switch (stringDate2) {
            case "monday":
                list = newLineMasterDao.findByMondayAndStatus(flag, "Active");
                break;
            case "tuesday":
                list = newLineMasterDao.findByTuesdayAndStatus(flag, "Active");
                break;
            case "wednesday":
                list = newLineMasterDao.findByWednesdayAndStatus(flag, "Active");
                break;
            case "thursday":
                list = newLineMasterDao.findByThursdayAndStatus(flag, "Active");
                break;
            case "friday":
                list = newLineMasterDao.findByFridayAndStatus(flag, "Active");
                break;
            case "saturday":
                list = newLineMasterDao.findBySaturdayAndStatus(flag, "Active");
                break;
            case "sunday":
                list = newLineMasterDao.findBySundayAndStatus(flag, "Active");
                break;

            default:
                System.out.println("aa");
        }
//        System.out.println("dfgh=>" + list.size());

        try {

            Date todaysdate = new Date();
//            System.out.println("todays date" + todaysdate);//todays date
            Calendar c = Calendar.getInstance();
            c.setTime(todaysdate);
            c.add(Calendar.DATE, -1);
            todaysdate = c.getTime();


            SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
            String stringDate1 = sdf5.format(todaysdate);


            System.out.println("before date1 and todays date" + stringDate1);
            for(LineMaster lineMaster:lineMaster2){
                SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy-MM-dd");
                String stringDate8 = sdf7.format(lineMaster.getLineDate());
                System.out.println("line Date''''''''"+stringDate8);

                if(stringDate1.equals(stringDate8)){
                    System.out.println("lineMaster1111111"+ lineMaster.toString());
                    Integer updatevalue = lineMasterDao.updateStatusToInActive1(lineMaster.getLineId());
                    System.out.println("updatevalue.................!"+updatevalue);

                }

            }

//            Integer updatevalue = lineMasterDao.updateStatusToInActive(todaysdate);
//            System.out.println("updatevalue.................!"+updatevalue);

            for (NewLineMaster newLineMaster : list) {
                LineMaster lineMaster = new LineMaster();


                switch (stringDate2){
                    case "monday":
                        if(newLineMaster.getMonday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getMonday().equals(false)){
                            lineMaster.setDailyStatus(false);

                        }
                        break;
                    case "tuesday":
                        if(newLineMaster.getTuesday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getTuesday().equals(false)){
                            lineMaster.setDailyStatus(false);
                        }
                        break;
                    case "wednesday":
                        if(newLineMaster.getWednesday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getWednesday().equals(false)){
                            lineMaster.setDailyStatus(false);
                        }
                        break;
                    case "thursday":
                        if(newLineMaster.getThursday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getThursday().equals(false)){
                            lineMaster.setDailyStatus(false);
                        }
                        break;
                    case "friday":
                        if(newLineMaster.getFriday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getFriday().equals(false)){
                            lineMaster.setDailyStatus(false);
                        }
                        break;
                    case "saturday":
                        if(newLineMaster.getSaturday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getSaturday().equals(false)){
                            lineMaster.setDailyStatus(false);
                        }
                        break;
                    case "sunday":
                        if(newLineMaster.getSunday().equals(true)){
                            lineMaster.setDailyStatus(true);
                        }else if(newLineMaster.getSunday().equals(false)){
                            lineMaster.setDailyStatus(false);
                        }
                        break;
                    default:
                        System.out.println("aa........");
                }

                UserMaster userMaster = new UserMaster();
                userMaster.setId(1L);
                lineMaster.setUserMaster(userMaster);
                lineMaster.setLineDate(new Date());
                lineMaster.setLineName(newLineMaster.getNewlineName());
                lineMaster.setLineStartTime(newLineMaster.getNewlineStartTime());
                lineMaster.setLineEndTime(newLineMaster.getNewlineEndTime());
                lineMaster.setLineTime(newLineMaster.getLineTime());
                lineMaster.setStatus("Active");
                lineMaster.setOpenStatus(true);
                lineMaster.setFinalStatus(true);
                try {
                    lineMasterDao.save(lineMaster);


                    lineMaster = new LineMaster();
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        //return flag;


        return list;


    }

    @Override
    public List getResultNewLine() {

        List<NewLineMaster> newLineMasters=newLineMasterDao.getNewLineDataStatus("Active");
        List<LineMaster> lineMasters=lineMasterDao.getLineMasterStatus("Active");
        for(int i=0;i<newLineMasters.size();i++){
            for(LineMaster lineMaster:lineMasters){
                if(newLineMasters.get(i).getNewlineName().equals(lineMaster.getLineName())){
                    newLineMasters.get(i).setLineOpenNo(lineMaster.getLineOpenNo());
                    newLineMasters.get(i).setLineMidNo(lineMaster.getLineMidNo());
                    newLineMasters.get(i).setLineCloseNo(lineMaster.getLineCloseNo());
                    System.out.println("hh"+newLineMasters.toString());
                }

            }

        }return newLineMasters;
    }

    @Override
    public Boolean addLineTime() throws ParseException {
        Boolean flag=false;
        List<NewLineMaster> newLineMasters=newLineMasterDao.findByStatusAndLineTime("Active",null);
        System.out.println("lineList"+newLineMasters);
        for (NewLineMaster newLineMaster:newLineMasters)
        {
            try {
                String pattern = "HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(newLineMaster.getNewlineStartTime());
                Date date1 = new SimpleDateFormat(pattern).parse(date);
                Integer update=newLineMasterDao.updateLineTime(date1,newLineMaster.getNewlineId());
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

}
