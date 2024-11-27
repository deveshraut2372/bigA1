package com.example.gameDemo.controller;


import com.example.gameDemo.model.LineMaster;
import com.example.gameDemo.payload.req.LineMasterReqDto;
import com.example.gameDemo.payload.req.ResultChartFinalResDto;
import com.example.gameDemo.payload.res.LineStatusResDto;
import com.example.gameDemo.service.LineMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lineMaster")
public class LineMasterController {

        @Autowired
        private LineMasterService lineMasterService;

        @PostMapping(value = "/createLineMaster")
        public ResponseEntity createLineMaster(@RequestBody LineMasterReqDto lineMasterReqDto) {
                Boolean flag = lineMasterService.createLineMaster(lineMasterReqDto);
                if (flag) {
                        return new ResponseEntity(flag, HttpStatus.CREATED);
                } else {
                        return new ResponseEntity(flag, HttpStatus.BAD_REQUEST);
                }
        }

        @PutMapping(value = "/updateLineMaster")
        public ResponseEntity updateLineMaster(@RequestBody LineMasterReqDto lineMasterReqDto) {
                Boolean flag = lineMasterService.updateLineMaster(lineMasterReqDto);
                if (flag) {
                        return new ResponseEntity(flag, HttpStatus.CREATED);
                } else {
                        return new ResponseEntity(flag, HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping(value = "/getActiveList")
        public ResponseEntity getActiveLines()
        {
                List list = lineMasterService.getActiveLines();
                if(list.size()!=0)
                {
                        return new ResponseEntity(list,HttpStatus.OK);
                }
                else
                {
                        return new ResponseEntity(list,HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping(value = "/getAllActiveList")
        public ResponseEntity getAllActiveList()
        {
                List list = lineMasterService.getAllActiveList();
                if(list.size()!=0)
                {
                        return new ResponseEntity(list,HttpStatus.OK);
                }
                else
                {
                        return new ResponseEntity(list,HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping(value = "/getAllList/{id}")
        public ResponseEntity getAllAllLines(@PathVariable Long id)
        {
                List list = lineMasterService.getAllAllLines(id);
                if(list.size()!=0)
                {

                        return new ResponseEntity(list,HttpStatus.OK);
                }
                else
                {
                        return new ResponseEntity(list,HttpStatus.BAD_REQUEST);
                }
        }
        @GetMapping(value = "/getAllLineList")
        public ResponseEntity getAllAllLinesList()
        {
                List list = lineMasterService.getAllAllLinesList();
                return new ResponseEntity(list,HttpStatus.OK);

        }
        @GetMapping(value = "/editLine/{lineId}")
        public ResponseEntity editLine(@PathVariable Integer lineId)
        {
                LineMasterReqDto lineMasterReqDto = lineMasterService.editLine(lineId);
                if(lineMasterReqDto!=null)
                {

                        return new ResponseEntity(lineMasterReqDto, HttpStatus.OK);
                }
                else
                {
                        return new ResponseEntity(lineMasterReqDto,HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping(value = "/getLineStatus/{lineId}")
        public ResponseEntity getLineStatus(@PathVariable Integer lineId)
        {
                LineStatusResDto lineStatusResDto=lineMasterService.getLineStatus(lineId);

                if(lineStatusResDto!=null)
                {
                        return new ResponseEntity(lineStatusResDto, HttpStatus.OK);
                }
                else
                {
                        return new ResponseEntity(lineStatusResDto,HttpStatus.BAD_REQUEST);
                }
        }

//
//        @PostMapping(value = "/getLineMasterByStartDateAndEndDate")
//        public ResponseEntity getLineMasterByStartDateAndEndDate(@RequestBody LineMasterStartDateReqDto lineMasterStartDateReqDto)
//        {
//               List<LineMaster> lineMasters =lineMasterService.getLineMasterByStartDateAndEndDate(lineMasterStartDateReqDto);
//
//               return  new ResponseEntity(lineMasters,HttpStatus.OK);
//        }

        @GetMapping(value = "/getActiveLinesYear/{name}")
        public ResponseEntity getActiveLinesYear(@PathVariable String name) {
                List list = lineMasterService.getActiveLinesYear(name);
                if (list.size() != 0) {
                        return new ResponseEntity(list, HttpStatus.OK);
                } else {
                        return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        @GetMapping(value = "/getPendingResultLines")
        public ResponseEntity getPendingResultLines() {
                List list = lineMasterService.getPendingResultLines();
                if (list.size() != 0) {
                        return new ResponseEntity(list, HttpStatus.OK);
                } else {
                        return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        @GetMapping(value = "/addLineTime")
        public ResponseEntity addLineTime() throws ParseException {
                Boolean flag=lineMasterService.addLineTime();
                return new ResponseEntity(flag,HttpStatus.OK);
        }


        @GetMapping(value = "/getResult/{name}/{year}")
        public ResponseEntity getResult(@PathVariable String name,@PathVariable String year)
        {
                List list = lineMasterService.getResult(name,year);
                return new ResponseEntity(list,HttpStatus.OK);

        }
        @GetMapping(value = "/byname/{name}/{year}")
        private ResponseEntity getResultById(@PathVariable String name,@PathVariable String year) throws ParseException, ParseException {
                List<ResultChartFinalResDto> resultChartFinalResDtoList = new ArrayList<ResultChartFinalResDto>();
                List<ResultChartFinalResDto> resultChartFinalResDtoList1 = new ArrayList<ResultChartFinalResDto>();
                List<LineMaster> list = new ArrayList<LineMaster>();
                List<LineMaster> list1=new ArrayList<LineMaster>();

                list1 = lineMasterService.getAllResultByName(name);

                for(LineMaster lineMaster:list1){
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                        String stringDate2 = sdf2.format(lineMaster.getLineDate());
//                        System.out.println("stringDate2..........."+stringDate2);
                        if(stringDate2.equals(year)){
                                list.add(lineMaster);
                        }

                }


                list.sort(Comparator.comparing(LineMaster::getLineDate));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



                ResultChartFinalResDto resultChartFinalResDto1 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto2 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto3 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto4 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto5 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto6 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto7 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto8 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto9 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto10 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto11 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto12 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto13 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto14 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto15 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto16 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto17 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto18 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto19 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto20 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto21 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto22 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto23 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto24 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto25 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto26 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto27 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto28 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto29 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto30 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto31 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto32 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto33 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto34 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto35 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto36 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto37 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto38 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto39 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto40 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto41 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto42 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto43 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto44 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto45 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto46 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto47 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto48 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto49 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto50 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto51 = new ResultChartFinalResDto();
                ResultChartFinalResDto resultChartFinalResDto52 = new ResultChartFinalResDto();

                for (LineMaster lineMaster : list) {

                        Calendar calendar = Calendar.getInstance(new Locale("uk","UA"));
                        calendar.setTime(lineMaster.getLineDate());
                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 1) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto1.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto1.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto1.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto1.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto1.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto1.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto1.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto1.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto1.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto1.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto1.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 2) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto2.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto2.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto2.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto2.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto2.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto2.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto2.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto2.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto2.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto2.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto2.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 3) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto3.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto3.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto3.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto3.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto3.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto3.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto3.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto3.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto3.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto3.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto3.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 4) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto4.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto4.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto4.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto4.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto4.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto4.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto4.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto4.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto4.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto4.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto4.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 5) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto5.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto5.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto5.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto5.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto5.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto5.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto5.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto5.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto5.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto5.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto5.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 6) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto6.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto6.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto6.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto6.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto6.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto6.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto6.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto6.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto6.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto6.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto6.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 7) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto7.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto7.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto7.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto7.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto7.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto7.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto7.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto7.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto7.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto7.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto7.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 8) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto8.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto8.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto8.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto8.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto8.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto8.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto8.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto8.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto8.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto8.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto8.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 9) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto9.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto9.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto9.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto9.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto9.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto9.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto9.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto9.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto9.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto9.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto9.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 10) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto10.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto10.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto10.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto10.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto10.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto10.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto10.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto10.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto10.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto10.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto10.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 11) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto11.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto11.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto11.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto11.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto11.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto11.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto11.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto11.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto11.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto11.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto11.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 12) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto12.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto12.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto12.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto12.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto12.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto12.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto12.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto12.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto12.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto12.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto12.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 13) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto13.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto13.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto13.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto13.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto13.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto13.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto13.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto13.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto13.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto13.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto13.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 14) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto14.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto14.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto14.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto14.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto14.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto14.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto14.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto14.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto14.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto14.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto14.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 15) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto15.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto15.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto15.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto15.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto15.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto15.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto15.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto15.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto15.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto15.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto15.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 16) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto16.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto16.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto16.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto16.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto16.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto16.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto16.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto16.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto16.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto16.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto16.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 17) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);
//                                System.out.println("df"+ i);

                                if(resultChartFinalResDto17.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto17.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto17.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto17.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto17.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto17.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto17.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto17.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto17.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto17.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto17.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 18) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto18.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto18.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto18.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto18.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto18.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto18.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto18.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto18.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto18.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto18.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto18.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 19) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto19.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto19.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto19.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto19.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto19.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto19.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto19.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto19.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto19.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto19.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto19.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 20) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                System.out.println("calendar.getTime()"+calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto20.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto20.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto20.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto20.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto20.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto20.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto20.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto20.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto20.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto20.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto20.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 21) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto21.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto21.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto21.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto21.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto21.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto21.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto21.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto21.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto21.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto21.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto21.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 22) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());

                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto22.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto22.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto22.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto22.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto22.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto22.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto22.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto22.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto22.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto22.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto22.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 23) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                System.out.println("calendar.getTime()"+calendar.getTime() + "WE"+calendar.get(Calendar.DAY_OF_WEEK));

                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto23.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto23.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto23.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto23.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto23.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto23.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto23.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto23.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto23.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto23.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto23.setResultChart6(lineMaster);
                                }


                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 24) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());

                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto24.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto24.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto24.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto24.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto24.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto24.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto24.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto24.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto24.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto24.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto24.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 25) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto25.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto25.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto25.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto25.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto25.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto25.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto25.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto25.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto25.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto25.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto25.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 26) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto26.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto26.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto26.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto26.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto26.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto26.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto26.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto26.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto26.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto26.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto26.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 27) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto27.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto27.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto27.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto27.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto27.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto27.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto27.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto27.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto27.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto27.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto27.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 28) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto28.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto28.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto28.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto28.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto28.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto28.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto28.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto28.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto28.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto28.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto28.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 29) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto29.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto29.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto29.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto29.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto29.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto29.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto29.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto29.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto29.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto29.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto29.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 30) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto30.getResultChart2()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto30.setResultChart2(startDateResult);
                                }
                                if(resultChartFinalResDto30.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto30.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto30.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto30.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto30.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto30.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto30.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto30.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto30.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 31) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto31.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto31.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto31.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto31.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto31.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto31.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto31.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto31.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto31.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto31.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto31.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 32) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);


                                if(resultChartFinalResDto32.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto32.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto32.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto32.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto32.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto32.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto32.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto32.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto32.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto32.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto32.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 33) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto33.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto33.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto33.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto33.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto33.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto33.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto33.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto33.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto33.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto33.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto33.setResultChart6(lineMaster);
                                }
                        }


                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 34) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto34.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto34.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto34.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto34.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto34.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto34.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto34.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto34.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto34.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto34.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto34.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 35) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto35.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto35.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto35.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto35.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto35.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto35.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto35.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto35.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto35.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto35.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto35.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 36) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto36.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto36.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto36.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto36.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto36.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto36.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto36.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto36.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto36.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto36.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto36.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 37) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto37.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto37.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto37.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto37.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto37.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto37.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto37.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto37.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto37.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto37.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto37.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 38) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto38.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto38.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto38.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto38.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto38.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto38.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto38.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto38.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto38.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto38.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto38.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 39) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto39.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto39.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto39.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto39.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto39.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto39.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto39.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto39.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto39.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto39.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto39.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 40){
                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto40.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto40.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto40.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto40.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto40.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto40.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto40.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto40.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto40.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto40.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto40.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 41) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto41.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto41.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto41.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto41.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto41.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto41.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto41.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto41.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto41.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto41.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto41.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 42) {



                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto42.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
//                                        startDateResult.setStringDate(sdf.format(calendar1.getTime()));
                                        resultChartFinalResDto42.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto42.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto42.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto42.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto42.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto42.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto42.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto42.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto42.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto42.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 43) {


                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto43.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto43.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto43.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto43.setResultChart7(endDateResult);
                                }


                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {

                                        resultChartFinalResDto43.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto43.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto43.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto43.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto43.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto43.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {

                                        resultChartFinalResDto43.setResultChart6(lineMaster);
                                }

                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 44) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto44.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto44.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto44.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto44.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto44.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto44.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto44.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto44.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto44.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto44.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto44.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 45) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto45.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto45.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto45.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto45.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto45.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {

                                        resultChartFinalResDto45.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto45.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto45.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto45.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto45.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto45.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 46) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto46.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto46.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto46.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto46.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto46.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto46.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto46.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto46.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto46.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto46.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto46.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 47) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto47.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto47.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto47.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto47.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto47.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto47.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto47.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto47.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto47.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto47.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto47.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 48) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto48.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto48.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto48.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto48.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto48.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto48.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto48.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto48.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto48.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto48.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto48.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 49) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto49.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto49.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto49.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto49.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto49.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto49.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto49.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto49.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto49.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto49.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto49.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 50) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto50.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto50.setResultChart1(startDateResult);
                                }
                                if(resultChartFinalResDto50.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto50.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto50.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto50.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto50.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto50.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto50.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto50.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto50.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 51) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto51.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto51.setResultChart1(startDateResult);
                                }

                                if(resultChartFinalResDto51.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto51.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto51.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto51.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto51.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto51.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto51.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto51.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto51.setResultChart6(lineMaster);
                                }
                        }

                        if (calendar.get(Calendar.WEEK_OF_YEAR) == 52) {

                                Calendar calendar1 = Calendar.getInstance(new Locale("uk","UA"));
                                calendar1.setTime(calendar.getTime());
                                int i = calendar1.get(Calendar.DAY_OF_WEEK) - calendar1.getFirstDayOfWeek();
                                calendar1.add(Calendar.DATE, -i);

                                if(resultChartFinalResDto52.getResultChart1()==null) {
                                        LineMaster startDateResult = new LineMaster();
                                        startDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto52.setResultChart1(startDateResult);
                                }

                                if(resultChartFinalResDto52.getResultChart7()==null) {
                                        calendar1.add(Calendar.DATE, 6);
                                        LineMaster endDateResult = new LineMaster();
                                        endDateResult.setLineDate(calendar1.getTime());
                                        resultChartFinalResDto52.setResultChart7(endDateResult);
                                }

                                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        resultChartFinalResDto52.setResultChart7(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                                        resultChartFinalResDto52.setResultChart1(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                                        resultChartFinalResDto52.setResultChart2(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                                        resultChartFinalResDto52.setResultChart3(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                                        resultChartFinalResDto52.setResultChart4(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                        resultChartFinalResDto52.setResultChart5(lineMaster);
                                }
                                if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                        resultChartFinalResDto52.setResultChart6(lineMaster);
                                }
                        }


                }



                if (resultChartFinalResDto2.getResultChart1() != null || resultChartFinalResDto2.getResultChart2() != null || resultChartFinalResDto2.getResultChart3() != null || resultChartFinalResDto2.getResultChart4() != null || resultChartFinalResDto2.getResultChart5() != null || resultChartFinalResDto2.getResultChart6() != null || resultChartFinalResDto2.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto2);
                }

                if (resultChartFinalResDto3.getResultChart1() != null || resultChartFinalResDto3.getResultChart2() != null || resultChartFinalResDto3.getResultChart3() != null || resultChartFinalResDto3.getResultChart4() != null || resultChartFinalResDto3.getResultChart5() != null || resultChartFinalResDto3.getResultChart6() != null || resultChartFinalResDto3.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto3);
                }

                if (resultChartFinalResDto4.getResultChart1() != null || resultChartFinalResDto4.getResultChart2() != null || resultChartFinalResDto4.getResultChart3() != null || resultChartFinalResDto4.getResultChart4() != null || resultChartFinalResDto4.getResultChart5() != null || resultChartFinalResDto4.getResultChart6() != null || resultChartFinalResDto4.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto4);
                }

                if (resultChartFinalResDto5.getResultChart1() != null || resultChartFinalResDto5.getResultChart2() != null || resultChartFinalResDto5.getResultChart3() != null || resultChartFinalResDto5.getResultChart4() != null || resultChartFinalResDto5.getResultChart5() != null || resultChartFinalResDto5.getResultChart6() != null || resultChartFinalResDto5.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto5);
                }

                if (resultChartFinalResDto6.getResultChart1() != null || resultChartFinalResDto6.getResultChart2() != null || resultChartFinalResDto6.getResultChart3() != null || resultChartFinalResDto6.getResultChart4() != null || resultChartFinalResDto6.getResultChart5() != null || resultChartFinalResDto6.getResultChart6() != null || resultChartFinalResDto6.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto6);
                }

                if (resultChartFinalResDto7.getResultChart1() != null || resultChartFinalResDto7.getResultChart2() != null || resultChartFinalResDto7.getResultChart3() != null || resultChartFinalResDto7.getResultChart4() != null || resultChartFinalResDto7.getResultChart5() != null || resultChartFinalResDto7.getResultChart6() != null || resultChartFinalResDto7.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto7);
                }

                if (resultChartFinalResDto8.getResultChart1() != null || resultChartFinalResDto8.getResultChart2() != null || resultChartFinalResDto8.getResultChart3() != null || resultChartFinalResDto8.getResultChart4() != null || resultChartFinalResDto8.getResultChart5() != null || resultChartFinalResDto8.getResultChart6() != null || resultChartFinalResDto8.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto8);
                }

                if (resultChartFinalResDto9.getResultChart1() != null || resultChartFinalResDto9.getResultChart2() != null || resultChartFinalResDto9.getResultChart3() != null || resultChartFinalResDto9.getResultChart4() != null || resultChartFinalResDto9.getResultChart5() != null || resultChartFinalResDto9.getResultChart6() != null || resultChartFinalResDto9.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto9);
                }

                if (resultChartFinalResDto10.getResultChart1() != null || resultChartFinalResDto10.getResultChart2() != null || resultChartFinalResDto10.getResultChart3() != null || resultChartFinalResDto10.getResultChart4() != null || resultChartFinalResDto10.getResultChart5() != null || resultChartFinalResDto10.getResultChart6() != null || resultChartFinalResDto10.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto10);
                }

                if (resultChartFinalResDto11.getResultChart1() != null || resultChartFinalResDto11.getResultChart2() != null || resultChartFinalResDto11.getResultChart3() != null || resultChartFinalResDto11.getResultChart4() != null || resultChartFinalResDto11.getResultChart5() != null || resultChartFinalResDto11.getResultChart6() != null || resultChartFinalResDto11.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto11);
                }

                if (resultChartFinalResDto12.getResultChart1() != null || resultChartFinalResDto12.getResultChart2() != null || resultChartFinalResDto12.getResultChart3() != null || resultChartFinalResDto12.getResultChart4() != null || resultChartFinalResDto12.getResultChart5() != null || resultChartFinalResDto12.getResultChart6() != null || resultChartFinalResDto12.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto12);
                }

                if (resultChartFinalResDto13.getResultChart1() != null || resultChartFinalResDto13.getResultChart2() != null || resultChartFinalResDto13.getResultChart3() != null || resultChartFinalResDto13.getResultChart4() != null || resultChartFinalResDto13.getResultChart5() != null || resultChartFinalResDto13.getResultChart6() != null || resultChartFinalResDto13.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto13);
                }

                if (resultChartFinalResDto14.getResultChart1() != null || resultChartFinalResDto14.getResultChart2() != null || resultChartFinalResDto14.getResultChart3() != null || resultChartFinalResDto14.getResultChart4() != null || resultChartFinalResDto14.getResultChart5() != null || resultChartFinalResDto14.getResultChart6() != null || resultChartFinalResDto14.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto14);
                }

                if (resultChartFinalResDto15.getResultChart1() != null || resultChartFinalResDto15.getResultChart2() != null || resultChartFinalResDto15.getResultChart3() != null || resultChartFinalResDto15.getResultChart4() != null || resultChartFinalResDto15.getResultChart5() != null || resultChartFinalResDto15.getResultChart6() != null || resultChartFinalResDto15.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto15);
                }

                if (resultChartFinalResDto16.getResultChart1() != null || resultChartFinalResDto16.getResultChart2() != null || resultChartFinalResDto16.getResultChart3() != null || resultChartFinalResDto16.getResultChart4() != null || resultChartFinalResDto16.getResultChart5() != null || resultChartFinalResDto16.getResultChart6() != null || resultChartFinalResDto16.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto16);
                }

                if (resultChartFinalResDto17.getResultChart1() != null || resultChartFinalResDto17.getResultChart2() != null || resultChartFinalResDto17.getResultChart3() != null || resultChartFinalResDto17.getResultChart4() != null || resultChartFinalResDto17.getResultChart5() != null || resultChartFinalResDto17.getResultChart6() != null || resultChartFinalResDto17.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto17);
                }

                if (resultChartFinalResDto18.getResultChart1() != null || resultChartFinalResDto18.getResultChart2() != null || resultChartFinalResDto18.getResultChart3() != null || resultChartFinalResDto18.getResultChart4() != null || resultChartFinalResDto18.getResultChart5() != null || resultChartFinalResDto18.getResultChart6() != null || resultChartFinalResDto18.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto18);
                }

                if (resultChartFinalResDto19.getResultChart1() != null || resultChartFinalResDto19.getResultChart2() != null || resultChartFinalResDto19.getResultChart3() != null || resultChartFinalResDto19.getResultChart4() != null || resultChartFinalResDto19.getResultChart5() != null || resultChartFinalResDto19.getResultChart6() != null || resultChartFinalResDto19.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto19);
                }

                if (resultChartFinalResDto20.getResultChart1() != null || resultChartFinalResDto20.getResultChart2() != null || resultChartFinalResDto20.getResultChart3() != null || resultChartFinalResDto20.getResultChart4() != null || resultChartFinalResDto20.getResultChart5() != null || resultChartFinalResDto20.getResultChart6() != null || resultChartFinalResDto20.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto20);
                }

                if (resultChartFinalResDto21.getResultChart1() != null || resultChartFinalResDto21.getResultChart2() != null || resultChartFinalResDto21.getResultChart3() != null || resultChartFinalResDto21.getResultChart4() != null || resultChartFinalResDto21.getResultChart5() != null || resultChartFinalResDto21.getResultChart6() != null || resultChartFinalResDto21.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto21);
                }

                if (resultChartFinalResDto22.getResultChart1() != null || resultChartFinalResDto22.getResultChart2() != null || resultChartFinalResDto22.getResultChart3() != null || resultChartFinalResDto22.getResultChart4() != null || resultChartFinalResDto22.getResultChart5() != null || resultChartFinalResDto22.getResultChart6() != null || resultChartFinalResDto22.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto22);
                }

                if (resultChartFinalResDto23.getResultChart1() != null || resultChartFinalResDto23.getResultChart2() != null || resultChartFinalResDto23.getResultChart3() != null || resultChartFinalResDto23.getResultChart4() != null || resultChartFinalResDto23.getResultChart5() != null || resultChartFinalResDto23.getResultChart6() != null || resultChartFinalResDto23.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto23);
                }

                if (resultChartFinalResDto24.getResultChart1() != null || resultChartFinalResDto24.getResultChart2() != null || resultChartFinalResDto24.getResultChart3() != null || resultChartFinalResDto24.getResultChart4() != null || resultChartFinalResDto24.getResultChart5() != null || resultChartFinalResDto24.getResultChart6() != null || resultChartFinalResDto24.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto24);
                }

                if (resultChartFinalResDto25.getResultChart1() != null || resultChartFinalResDto25.getResultChart2() != null || resultChartFinalResDto25.getResultChart3() != null || resultChartFinalResDto25.getResultChart4() != null || resultChartFinalResDto25.getResultChart5() != null || resultChartFinalResDto25.getResultChart6() != null || resultChartFinalResDto25.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto25);
                }

                if (resultChartFinalResDto26.getResultChart1() != null || resultChartFinalResDto26.getResultChart2() != null || resultChartFinalResDto26.getResultChart3() != null || resultChartFinalResDto26.getResultChart4() != null || resultChartFinalResDto26.getResultChart5() != null || resultChartFinalResDto26.getResultChart6() != null || resultChartFinalResDto26.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto26);
                }

                if (resultChartFinalResDto27.getResultChart1() != null || resultChartFinalResDto27.getResultChart2() != null || resultChartFinalResDto27.getResultChart3() != null || resultChartFinalResDto27.getResultChart4() != null || resultChartFinalResDto27.getResultChart5() != null || resultChartFinalResDto27.getResultChart6() != null || resultChartFinalResDto27.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto27);
                }

                if (resultChartFinalResDto28.getResultChart1() != null || resultChartFinalResDto28.getResultChart2() != null || resultChartFinalResDto28.getResultChart3() != null || resultChartFinalResDto28.getResultChart4() != null || resultChartFinalResDto28.getResultChart5() != null || resultChartFinalResDto28.getResultChart6() != null || resultChartFinalResDto28.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto28);
                }

                if (resultChartFinalResDto29.getResultChart1() != null || resultChartFinalResDto29.getResultChart2() != null || resultChartFinalResDto29.getResultChart3() != null || resultChartFinalResDto29.getResultChart4() != null || resultChartFinalResDto29.getResultChart5() != null || resultChartFinalResDto29.getResultChart6() != null || resultChartFinalResDto29.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto29);
                }

                if (resultChartFinalResDto30.getResultChart1() != null || resultChartFinalResDto30.getResultChart2() != null || resultChartFinalResDto30.getResultChart3() != null || resultChartFinalResDto30.getResultChart4() != null || resultChartFinalResDto30.getResultChart5() != null || resultChartFinalResDto30.getResultChart6() != null || resultChartFinalResDto30.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto30);
                }

                if (resultChartFinalResDto31.getResultChart1() != null || resultChartFinalResDto31.getResultChart2() != null || resultChartFinalResDto31.getResultChart3() != null || resultChartFinalResDto31.getResultChart4() != null || resultChartFinalResDto31.getResultChart5() != null || resultChartFinalResDto31.getResultChart6() != null || resultChartFinalResDto31.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto31);
                }

                if (resultChartFinalResDto32.getResultChart1() != null || resultChartFinalResDto32.getResultChart2() != null || resultChartFinalResDto32.getResultChart3() != null || resultChartFinalResDto32.getResultChart4() != null || resultChartFinalResDto32.getResultChart5() != null || resultChartFinalResDto32.getResultChart6() != null || resultChartFinalResDto32.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto32);
                }

                if (resultChartFinalResDto33.getResultChart1() != null || resultChartFinalResDto33.getResultChart2() != null || resultChartFinalResDto33.getResultChart3() != null || resultChartFinalResDto33.getResultChart4() != null || resultChartFinalResDto33.getResultChart5() != null || resultChartFinalResDto33.getResultChart6() != null || resultChartFinalResDto33.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto33);
                }

                if (resultChartFinalResDto34.getResultChart1() != null || resultChartFinalResDto34.getResultChart2() != null || resultChartFinalResDto34.getResultChart3() != null || resultChartFinalResDto34.getResultChart4() != null || resultChartFinalResDto34.getResultChart5() != null || resultChartFinalResDto34.getResultChart6() != null || resultChartFinalResDto34.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto34);
                }

                if (resultChartFinalResDto35.getResultChart1() != null || resultChartFinalResDto35.getResultChart2() != null || resultChartFinalResDto35.getResultChart3() != null || resultChartFinalResDto35.getResultChart4() != null || resultChartFinalResDto35.getResultChart5() != null || resultChartFinalResDto35.getResultChart6() != null || resultChartFinalResDto35.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto35);
                }

                if (resultChartFinalResDto36.getResultChart1() != null || resultChartFinalResDto36.getResultChart2() != null || resultChartFinalResDto36.getResultChart3() != null || resultChartFinalResDto36.getResultChart4() != null || resultChartFinalResDto36.getResultChart5() != null || resultChartFinalResDto36.getResultChart6() != null || resultChartFinalResDto36.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto36);
                }

                if (resultChartFinalResDto37.getResultChart1() != null || resultChartFinalResDto37.getResultChart2() != null || resultChartFinalResDto37.getResultChart3() != null || resultChartFinalResDto37.getResultChart4() != null || resultChartFinalResDto37.getResultChart5() != null || resultChartFinalResDto37.getResultChart6() != null || resultChartFinalResDto37.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto37);
                }

                if (resultChartFinalResDto38.getResultChart1() != null || resultChartFinalResDto38.getResultChart2() != null || resultChartFinalResDto38.getResultChart3() != null || resultChartFinalResDto38.getResultChart4() != null || resultChartFinalResDto38.getResultChart5() != null || resultChartFinalResDto38.getResultChart6() != null || resultChartFinalResDto38.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto38);
                }

                if (resultChartFinalResDto39.getResultChart1() != null || resultChartFinalResDto39.getResultChart2() != null || resultChartFinalResDto39.getResultChart3() != null || resultChartFinalResDto39.getResultChart4() != null || resultChartFinalResDto39.getResultChart5() != null || resultChartFinalResDto39.getResultChart6() != null || resultChartFinalResDto39.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto39);
                }

                if (resultChartFinalResDto40.getResultChart1() != null || resultChartFinalResDto40.getResultChart2() != null || resultChartFinalResDto40.getResultChart3() != null || resultChartFinalResDto40.getResultChart4() != null || resultChartFinalResDto40.getResultChart5() != null || resultChartFinalResDto40.getResultChart6() != null || resultChartFinalResDto40.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto40);
                }


                if (resultChartFinalResDto41.getResultChart1() != null || resultChartFinalResDto41.getResultChart2() != null || resultChartFinalResDto41.getResultChart3() != null || resultChartFinalResDto41.getResultChart4() != null || resultChartFinalResDto41.getResultChart5() != null || resultChartFinalResDto41.getResultChart6() != null || resultChartFinalResDto41.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto41);
                }

                if (resultChartFinalResDto42.getResultChart1() != null || resultChartFinalResDto42.getResultChart2() != null || resultChartFinalResDto42.getResultChart3() != null || resultChartFinalResDto42.getResultChart4() != null || resultChartFinalResDto42.getResultChart5() != null || resultChartFinalResDto42.getResultChart6() != null || resultChartFinalResDto42.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto42);
                }

                if (resultChartFinalResDto43.getResultChart1() != null || resultChartFinalResDto43.getResultChart2() != null || resultChartFinalResDto43.getResultChart3() != null || resultChartFinalResDto43.getResultChart4() != null || resultChartFinalResDto43.getResultChart5() != null || resultChartFinalResDto43.getResultChart6() != null || resultChartFinalResDto43.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto43);
                }

                if (resultChartFinalResDto44.getResultChart1() != null || resultChartFinalResDto44.getResultChart2() != null || resultChartFinalResDto44.getResultChart3() != null || resultChartFinalResDto44.getResultChart4() != null || resultChartFinalResDto44.getResultChart5() != null || resultChartFinalResDto44.getResultChart6() != null || resultChartFinalResDto44.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto44);
                }

                if (resultChartFinalResDto45.getResultChart1() != null || resultChartFinalResDto45.getResultChart2() != null || resultChartFinalResDto45.getResultChart3() != null || resultChartFinalResDto45.getResultChart4() != null || resultChartFinalResDto45.getResultChart5() != null || resultChartFinalResDto45.getResultChart6() != null || resultChartFinalResDto45.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto45);
                }

                if (resultChartFinalResDto46.getResultChart1() != null || resultChartFinalResDto46.getResultChart2() != null || resultChartFinalResDto46.getResultChart3() != null || resultChartFinalResDto46.getResultChart4() != null || resultChartFinalResDto46.getResultChart5() != null || resultChartFinalResDto46.getResultChart6() != null || resultChartFinalResDto46.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto46);
                }

                if (resultChartFinalResDto47.getResultChart1() != null || resultChartFinalResDto47.getResultChart2() != null || resultChartFinalResDto47.getResultChart3() != null || resultChartFinalResDto47.getResultChart4() != null || resultChartFinalResDto47.getResultChart5() != null || resultChartFinalResDto47.getResultChart6() != null || resultChartFinalResDto47.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto47);
                }

                if (resultChartFinalResDto48.getResultChart1() != null || resultChartFinalResDto48.getResultChart2() != null || resultChartFinalResDto48.getResultChart3() != null || resultChartFinalResDto48.getResultChart4() != null || resultChartFinalResDto48.getResultChart5() != null || resultChartFinalResDto48.getResultChart6() != null || resultChartFinalResDto48.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto48);
                }

                if (resultChartFinalResDto49.getResultChart1() != null || resultChartFinalResDto49.getResultChart2() != null || resultChartFinalResDto49.getResultChart3() != null || resultChartFinalResDto49.getResultChart4() != null || resultChartFinalResDto49.getResultChart5() != null || resultChartFinalResDto49.getResultChart6() != null || resultChartFinalResDto49.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto49);
                }

                if (resultChartFinalResDto50.getResultChart1() != null || resultChartFinalResDto50.getResultChart2() != null || resultChartFinalResDto50.getResultChart3() != null || resultChartFinalResDto50.getResultChart4() != null || resultChartFinalResDto50.getResultChart5() != null || resultChartFinalResDto50.getResultChart6() != null || resultChartFinalResDto50.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto50);
                }

                if (resultChartFinalResDto51.getResultChart1() != null || resultChartFinalResDto51.getResultChart2() != null || resultChartFinalResDto51.getResultChart3() != null || resultChartFinalResDto51.getResultChart4() != null || resultChartFinalResDto51.getResultChart5() != null || resultChartFinalResDto51.getResultChart6() != null || resultChartFinalResDto51.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto51);
                }

                if (resultChartFinalResDto52.getResultChart1() != null || resultChartFinalResDto52.getResultChart2() != null || resultChartFinalResDto52.getResultChart3() != null || resultChartFinalResDto52.getResultChart4() != null || resultChartFinalResDto52.getResultChart5() != null || resultChartFinalResDto52.getResultChart6() != null || resultChartFinalResDto52.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto52);
                }

                if (resultChartFinalResDto1.getResultChart1() != null || resultChartFinalResDto1.getResultChart2() != null || resultChartFinalResDto1.getResultChart3() != null || resultChartFinalResDto1.getResultChart4() != null || resultChartFinalResDto1.getResultChart5() != null || resultChartFinalResDto1.getResultChart6() != null || resultChartFinalResDto1.getResultChart7() != null) {
                        resultChartFinalResDtoList.add(resultChartFinalResDto1);
                }

                Integer[] randomEle={16,61,27,72,38,83,49,94,05,50,11,22,33,44,55,66,77,88,99,00};

                for(int i=0;i<resultChartFinalResDtoList.size();i++)
                {
                        // Chart 1
                        if (resultChartFinalResDtoList.get(i).getResultChart1()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart1().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart1().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart1().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart1().setRedFlag(false);
                                        }
                                }else {
                                        resultChartFinalResDtoList.get(i).getResultChart1().setRedFlag(false);
                                }
                        }

                        // Chart 2
                        if (resultChartFinalResDtoList.get(i).getResultChart2()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart2().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart2().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart2().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart2().setRedFlag(false);
                                        }
                                } else {
                                        resultChartFinalResDtoList.get(i).getResultChart2().setRedFlag(false);
                                }
                        }
                        // Chart 3
                        if (resultChartFinalResDtoList.get(i).getResultChart3()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart3().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart3().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart3().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart3().setRedFlag(false);
                                        }
                                } else {
                                        resultChartFinalResDtoList.get(i).getResultChart3().setRedFlag(false);
                                }
                        }
                        // Chart 4
                        if (resultChartFinalResDtoList.get(i).getResultChart4()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart4().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart4().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart4().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart4().setRedFlag(false);
                                        }
                                } else {
                                        resultChartFinalResDtoList.get(i).getResultChart4().setRedFlag(false);
                                }
                        }
                        // Chart 5
                        if (resultChartFinalResDtoList.get(i).getResultChart5()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart5().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart5().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart5().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart5().setRedFlag(false);
                                        }
                                } else {
                                        resultChartFinalResDtoList.get(i).getResultChart5().setRedFlag(false);
                                }
                        }
                        // Chart 6
                        if (resultChartFinalResDtoList.get(i).getResultChart6()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart6().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart6().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart6().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart6().setRedFlag(false);
                                        }
                                } else {
                                        resultChartFinalResDtoList.get(i).getResultChart6().setRedFlag(false);
                                }
                        }
                        // Chart 7
                        if (resultChartFinalResDtoList.get(i).getResultChart7()!=null) {
                                if (resultChartFinalResDtoList.get(i).getResultChart7().getLineMidNo() != null) {
                                        if (Arrays.asList(randomEle).contains(Integer.parseInt(resultChartFinalResDtoList.get(i).getResultChart7().getLineMidNo()))) {
                                                resultChartFinalResDtoList.get(i).getResultChart7().setRedFlag(true);
                                        } else {
                                                resultChartFinalResDtoList.get(i).getResultChart7().setRedFlag(false);
                                        }
                                } else {
                                        resultChartFinalResDtoList.get(i).getResultChart7().setRedFlag(false);
                                }
                        }
                }


                return new ResponseEntity(resultChartFinalResDtoList, HttpStatus.OK);
        }
}
