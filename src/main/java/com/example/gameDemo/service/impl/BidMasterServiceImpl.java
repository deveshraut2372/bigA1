package com.example.gameDemo.service.impl;


import com.example.gameDemo.configuration.RandomNumberGenerator;
import com.example.gameDemo.model.*;
import com.example.gameDemo.payload.req.*;
import com.example.gameDemo.payload.res.*;
import com.example.gameDemo.repository.*;
import com.example.gameDemo.service.BidMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BidMasterServiceImpl implements BidMasterService {

    @Autowired
    private BidMasterDao bidMasterDao;

    @Autowired
    private UserRepository userMasterDao;

    @Autowired
    private UserTransctionHistoryDao userTransctionHistoryDao;
    @Autowired
    private WiningHistoryDao winingHistoryDao;

    @Autowired
    private LineMasterDao lineMasterDao;

    @Autowired
    private GameRateMasterDao gameRateMasterDao;


    @Override
    public BidResDto createBidMaster(List<BidMasterReqDto> bidMasterReqDtoList) {

        System.out.println(" bidMasterReqDtoList size = "+bidMasterReqDtoList.size());
        BidResDto bidResDto = new BidResDto();
        Double balance = 0.0;

        Integer number = RandomNumberGenerator.getNumber();
        for (BidMasterReqDto bidMasterReqDto : bidMasterReqDtoList) {

            BidHistory bidHistory = new BidHistory();
            BeanUtils.copyProperties(bidMasterReqDto, bidHistory);

            UserMaster userMaster = new UserMaster();
            userMaster.setId(bidMasterReqDto.getId());
            bidHistory.setUserMaster(userMaster);

            LineMaster lineMaster = new LineMaster();
            lineMaster.setLineId(bidMasterReqDto.getLineId());
            bidHistory.setLineMaster(lineMaster);
            bidHistory.setBidDate(new Date());

            System.out.println("  bidHistory =="+bidHistory.toString());
            try {
                bidMasterDao.save(bidHistory);
                Integer no = userMasterDao.aprRejAgentFundRequestDao(-(bidMasterReqDto.getBidPoint()), bidMasterReqDto.getId());
                balance = userMasterDao.getId(bidMasterReqDto.getId());
                System.out.println(" balance ="+balance+"   no "+no);
            } catch (Exception e) {
                e.printStackTrace();
                bidResDto.setMsg("Betting Failed");
                bidResDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

                return bidResDto;
            }
            try {

                UserTransctionHistory userTransctionHistory1 = new UserTransctionHistory();
                userTransctionHistory1.setDateTime(new Date());
                userTransctionHistory1.setPoints(bidMasterReqDto.getBidPoint());
                userTransctionHistory1.setTranType("Bid");
                userTransctionHistory1.setTranStatus("Debit");
                userTransctionHistory1.setValue(bidMasterReqDto.getValue());
                UserMaster userMaster1 = new UserMaster();
                userMaster1=userMasterDao.findById(bidMasterReqDto.getId()).get();
//                userMaster1.setId(bidMasterReqDto.getId());
                userTransctionHistory1.setUserMaster(userMaster1);
                userTransctionHistory1.setDate(new Date());
                System.out.println(" User Master ="+userMaster1.toString());
                System.out.println(" walletr POints ="+userMaster1.getWalletPoints());

//                double points=userMaster1.getWalletPoints()-bidMasterReqDto.getBidPoint();
                userTransctionHistory1.setBalance(String.valueOf(balance));
                userTransctionHistory1.setLineName(bidMasterReqDto.getLineName());
                userTransctionHistory1.setSession(bidMasterReqDto.getSession());

                userMaster1.setWalletPoints(balance);
                UserMaster userMaster2=userMasterDao.save(userMaster1);
                System.out.println(" Afther walletr POints ="+userMaster2.getWalletPoints());
                try {
                    userTransctionHistory1.setUniqueNo(number.toString());
                    userTransctionHistoryDao.save(userTransctionHistory1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Integer commision = userMasterDao.getAgentCommision(bidMasterReqDto.getId());
                double percentageAMt = (bidMasterReqDto.getBidPoint() / 100) * commision;



//                AgentTransctionHistory agentTransctionHistory=new AgentTransctionHistory();
//                agentTransctionHistory.setDateAndTime(new Date());
//                agentTransctionHistory.setPoints(percentageAMt);
//                agentTransctionHistory.setTranType("Commision");
//                agentTransctionHistory.setTranStatus("Credit");
//                AgentMaster agentMaster=new AgentMaster();
//                agentMaster.setAgentId(bidMasterReqDto.getAgentId());
//                agentTransctionHistory.setAgentMaster(agentMaster);
//                agentFundTransctionDao.save(agentTransctionHistory);

//                Integer no1 = agentMasterDao.updateAgentPoints(percentageAMt, bidMasterReqDto.getAgentId());

            } catch (Exception e) {
                e.printStackTrace();
                bidResDto.setMsg("Betting Failed");
                bidResDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

                return bidResDto;
            }
        }


        bidResDto.setMsg("Betting Success");
        bidResDto.setResponseCode(HttpStatus.OK.value());
        bidResDto.setWalletPoints(balance);
        return bidResDto;
    }

    @Override
    public List getAllBidRequest(Long id) {
        UserMaster userMaster = new UserMaster();
        userMaster.setId(id);
        return (List) bidMasterDao.findAllByUserMasterOrderByBidDateDesc(userMaster);
    }

    @Override
    public Boolean openResultCal(ResultCalReqDto resultCalReqDto) {
        try {
            String ser = resultCalReqDto.getLineMidNo();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public List getBidMasterDetails(BidMasterReqDto bidMasterReqDto1) {

        List list = bidMasterDao.getBidMasterDetails(bidMasterReqDto1.getId(), bidMasterReqDto1.getLineId(), bidMasterReqDto1.getGameName(), bidMasterReqDto1.getLineName());
        return list;
    }

    @Override
    public List<BidValueCountResDto> getBidValueCount(BidMasterReqDto bidMasterReqDto1) {
        List<BidValueCountResDto> bidValueCountResDtos = bidMasterDao.getBidValueCount(bidMasterReqDto1.getId(), bidMasterReqDto1.getLineId(), bidMasterReqDto1.getGameName(), bidMasterReqDto1.getLineName());
        return bidValueCountResDtos;
    }

    @Override
    public TotalWinningResDto getTotalWinningAmount(Integer lineId) {
        TotalWinningResDto totalWinningResDto2 = new TotalWinningResDto();
        TotalWinningResDto totalBidResDto = bidMasterDao.getTotalWinningAmount(lineId);
        TotalWinningResDto totalWinningResDto1 = winingHistoryDao.getTotalWinningAmount(lineId);

        totalWinningResDto2.setTotalBidpointAmount(totalBidResDto.getTotalBidpointAmount());
        totalWinningResDto2.setTotalCountBidpoint(totalBidResDto.getTotalCountBidpoint());
        totalWinningResDto2.setTotalWinPointsAmount(totalWinningResDto1.getTotalWinPointsAmount());
        totalWinningResDto2.setTotalCountWinPoints(totalWinningResDto1.getTotalCountWinPoints());
        return totalWinningResDto2;
    }

    @Override
    public BarchartResDto getWeeklyListByAgentId(Long id) {

        List<Long> winingPointList = new ArrayList<>();
        List<Long> bidingPointList = new ArrayList<>();

        BarchartResDto barchartResDto = new BarchartResDto();
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int i = dayOfWeek - 1;

        while (i >= 0) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
            Date fromSundayToCurrentDate = cal.getTime();

            Long totalWinPoints = winingHistoryDao.getSumOfWiningPoint(fromSundayToCurrentDate, id);
            Long totalBidPoints = bidMasterDao.getSumOfBidPoint(fromSundayToCurrentDate, id);
            winingPointList.add(totalWinPoints);
            bidingPointList.add(totalBidPoints);
            i--;
        }

        //after current date

        int day = 1;
        for (int i1 = dayOfWeek + 1; i1 <= 7; i1++) {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, day);
            Date fromCurrentDateToSaturday = cal.getTime();


            Long totalWinPoints = winingHistoryDao.getSumOfWiningPoint(fromCurrentDateToSaturday, id);
            Long totalBidPoints = bidMasterDao.getSumOfBidPoint(fromCurrentDateToSaturday, id);
            winingPointList.add(totalWinPoints);
            bidingPointList.add(totalBidPoints);

            day++;
        }
        barchartResDto.setWiningPointList(winingPointList);
        barchartResDto.setBidingPointList(bidingPointList);
        return barchartResDto;
    }


    @Override
    public DashBoardCountResDto getTodaysBidCount(Long id) {
        Long todaysBidCnt = bidMasterDao.getTodaysBidCount(id, new Date());

        DashBoardCountResDto dashBoardCountResDto = new DashBoardCountResDto();
        dashBoardCountResDto.setTodayBidCnt(todaysBidCnt);

        return dashBoardCountResDto;
    }

    @Override
    public List getAllBidMasterList() {
        List<BidMasterResDto> bidMasterResDtoList = bidMasterDao.getAllBidMasterList();
        return bidMasterResDtoList;
    }

    @Override
    public List getdemoList(DemoReq demoReq) {
        List<ResultUserResDto> resultUserResDtoList = bidMasterDao.getdemoList(demoReq.getLineId(), demoReq.getGameRateName(), demoReq.getValue(), demoReq.getSession());
        return resultUserResDtoList;
    }

    @Override
    public List getBidHistory() {
        List<UserTranstionResDto> userTranstionResDtos = new ArrayList<>();
        try {
            List<UserTransctionHistory> userTransctionHistories = (List<UserTransctionHistory>) userTransctionHistoryDao.findAll();

            HashMap<String, UserTranstionResDto> uniqueNumberMap = new HashMap<>();

            for (UserTransctionHistory userTransctionHistory : userTransctionHistories) {
                String uniqueNo = userTransctionHistory.getUniqueNo();
                if (!uniqueNumberMap.containsKey(uniqueNo)) {
                    UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
                    userTranstionResDto.setUniqueNo(uniqueNo);
                    userTranstionResDto.setTranStatus(userTransctionHistory.getTranStatus());
                    userTranstionResDto.setSession(userTransctionHistory.getSession());
                    userTranstionResDto.setLineName(userTransctionHistory.getLineName());
                    userTranstionResDto.setTranType(userTransctionHistory.getTranType());
                    userTranstionResDto.setDateTime(userTransctionHistory.getDateTime());
                    userTranstionResDto.setUsername(userTransctionHistory.getUserMaster().getUsername());
                    userTranstionResDto.setMobileNo(userTransctionHistory.getUserMaster().getMobileNo());
                    List<TransationResDto> transationResDtos = userTransctionHistoryDao.getValue(uniqueNo);

                    double totalPoint = 0.0;
                    for (TransationResDto transationResDto : transationResDtos) {
                        System.out.println(transationResDto.getPoints());
                        totalPoint += transationResDto.getPoints();
                        System.out.println(totalPoint);
                    }
                    userTranstionResDto.setTotalPoint(totalPoint);
                    userTranstionResDto.setTransationResDtos(transationResDtos);
                    uniqueNumberMap.put(uniqueNo, userTranstionResDto);
                    userTranstionResDtos.add(userTranstionResDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userTranstionResDtos;
    }

    @Override
    public List getBusinessDateWise(DateReqDto dateReqDto) {

        if(dateReqDto.getBidDate()==null)
        {
            dateReqDto.setBidDate(new Date());
        }

        List<BidHistory> bidHistories = bidMasterDao.findAllByBidDate(dateReqDto.getBidDate());
        HashMap<String, LineResultResDto> lineName = new HashMap<>();
        List<LineHistoryTotalPointResDto> list = new ArrayList<>();
        List<LineResultResDto> tempList = new ArrayList<>();
        for (BidHistory bidHistory : bidHistories) {
            List<String> lineNames = bidMasterDao.getLineName(bidHistory.getLineMaster().getLineId(), dateReqDto.getBidDate());
            for (String lineNameStr : lineNames) {
                if (!lineName.containsKey(lineNameStr)) {
                    LineResultResDto lineHistoryResDto = new LineResultResDto();
                    lineHistoryResDto.setLineName(lineNameStr);
                    Double bidPoint = bidMasterDao.getWalletPoint(bidHistory.getLineMaster().getLineId(), dateReqDto.getBidDate());
                    lineHistoryResDto.setBidPoint(bidPoint);
                    lineName.put(lineNameStr, lineHistoryResDto);
                    tempList.add(lineHistoryResDto);
                }
            }
        }
        Double totalPoint = bidMasterDao.getSumOfBidPoints(dateReqDto.getBidDate());

        LineHistoryTotalPointResDto lineHistoryTotalPointResDto = new LineHistoryTotalPointResDto();
        for (LineResultResDto lineDto : tempList) {
            lineHistoryTotalPointResDto.setTotalBidPoint(totalPoint);
            lineHistoryTotalPointResDto.setLineResultResDtoList(tempList);
        }
        list.add(lineHistoryTotalPointResDto);
        return list;
    }
    @Override
    public List getBidReportOpen(BidReportReqDto bidReportReqDto) {
        System.out.println(" bidReportReqDto =="+bidReportReqDto.toString());

        if(bidReportReqDto.getLineName()==null)
        {

            List<LineMaster> lineMasters=new ArrayList<>();
            lineMasters=lineMasterDao.getLineMasterStatus("Active");
            LineMaster lineMaster=new LineMaster();
            lineMaster=lineMasters.stream().findFirst().get();

            List<GameRateMasterResDto> gameRateMasterResDtos=new ArrayList<>();
            gameRateMasterResDtos=gameRateMasterDao.getActiveList();
            GameRateMasterResDto gameRateMasterResDto=new GameRateMasterResDto();
            gameRateMasterResDto=gameRateMasterResDtos.stream().findFirst().get();

            bidReportReqDto.setBidDate(new Date());
            bidReportReqDto.setGameRateName(gameRateMasterResDto.getGameName());
            bidReportReqDto.setLineName(lineMaster.getLineName());
        }

        System.out.println(" bidReportReqDto =="+bidReportReqDto.toString());


        List<BidHistory> bidHistoryList = bidMasterDao.getBidReportOpen(bidReportReqDto.getBidDate(), bidReportReqDto.getGameRateName(), bidReportReqDto.getLineName());
        List<BidReportListResDto> bidReportListResDtoList = new ArrayList<>();
        List<BidReportResDto> list=new ArrayList<>();
        BidReportListResDto bidReportListResDto=new BidReportListResDto();
        HashMap<String, BidReportResDto> lineName = new HashMap<>();
        for(BidHistory bidHistory:bidHistoryList){

            List<String> value=bidMasterDao.getLineNameWise(bidReportReqDto.getBidDate(), bidReportReqDto.getGameRateName(), bidReportReqDto.getLineName());
            for (String lineNameStr : value) {
                if (!lineName.containsKey(lineNameStr)) {
                    BidReportResDto bidReportResDto = new BidReportResDto();
                    bidReportResDto.setValue(lineNameStr);
                    Double bidPoint = bidMasterDao.calculateBidPoints(lineNameStr,bidReportReqDto.getBidDate(),bidReportReqDto.getGameRateName(),"Open");

                    System.out.println("  bid ponitys ="+bidPoint);
                    bidReportResDto.setBidPoint(bidPoint);
                    lineName.put(lineNameStr, bidReportResDto);
                    list.add(bidReportResDto);
                }
            }
        }

        Double totalPoint = bidMasterDao.getSumOfBidPointsOpen(bidReportReqDto.getBidDate(),bidReportReqDto.getGameRateName());
        for (BidReportResDto lineDto : list) {
            bidReportListResDto.setBidReportResDtoList(list);
            bidReportListResDto.setTotalAmount(totalPoint);
        }
        bidReportListResDtoList.add(bidReportListResDto);
        return bidReportListResDtoList;
    }

    @Override
    public List getBidReportClose(BidReportReqDto bidReportReqDto) {

        System.out.println(" bidReportReqDto =="+bidReportReqDto.toString());

        if(bidReportReqDto.getLineName()==null)
        {
            List<LineMaster> lineMasters=new ArrayList<>();
            lineMasters=lineMasterDao.getLineMasterStatus("Active");
            LineMaster lineMaster=new LineMaster();
            lineMaster=lineMasters.stream().findFirst().get();

            List<GameRateMasterResDto> gameRateMasterResDtos=new ArrayList<>();
            gameRateMasterResDtos=gameRateMasterDao.getActiveList();
            GameRateMasterResDto gameRateMasterResDto=new GameRateMasterResDto();
            gameRateMasterResDto=gameRateMasterResDtos.stream().findFirst().get();

            bidReportReqDto.setBidDate(new Date());
            bidReportReqDto.setGameRateName(gameRateMasterResDto.getGameName());
            bidReportReqDto.setLineName(lineMaster.getLineName());
        }

        System.out.println(" bidReportReqDto =="+bidReportReqDto.toString());

        List<BidHistory> bidHistoryList = bidMasterDao.getBidReportClose(bidReportReqDto.getBidDate(), bidReportReqDto.getGameRateName(), bidReportReqDto.getLineName());
        System.out.println(bidHistoryList.size());
        List<BidReportListResDto> bidReportListResDtoList = new ArrayList<>();
        List<BidReportResDto> list=new ArrayList<>();
        BidReportListResDto bidReportListResDto=new BidReportListResDto();
        HashMap<String, BidReportResDto> lineName = new HashMap<>();
        for(BidHistory bidHistory:bidHistoryList){
            List<String> value=bidMasterDao.getLineNameWises(bidReportReqDto.getBidDate(), bidReportReqDto.getGameRateName(), bidReportReqDto.getLineName());
            for (String lineNameStr : value) {
                if (!lineName.containsKey(lineNameStr)) {
                    BidReportResDto bidReportResDto = new BidReportResDto();
                    bidReportResDto.setValue(lineNameStr);
                    Double bidPoint = bidMasterDao.calculateBidPoints(lineNameStr,bidReportReqDto.getBidDate(),bidReportReqDto.getGameRateName(),"Close");
                    System.out.println("  bid ponitys ="+bidPoint);
                    bidReportResDto.setBidPoint(bidPoint);
                    lineName.put(lineNameStr, bidReportResDto);
                    list.add(bidReportResDto);
                }
            }
        }
        Double totalPoint = bidMasterDao.getSumOfBidPointClose(bidReportReqDto.getBidDate(),bidReportReqDto.getGameRateName());
        for (BidReportResDto lineDto : list) {
            bidReportListResDto.setBidReportResDtoList(list);
            bidReportListResDto.setTotalAmount(totalPoint);
        }
        bidReportListResDtoList.add(bidReportListResDto);
        return bidReportListResDtoList;

}

    @Override
    public List getBidHistoryByDate(BidMasterDateReq bidMasterDateReq) {

        if(bidMasterDateReq.getBidDate()==null)
        {
            bidMasterDateReq.setBidDate(new Date());
        }

        List<UserTranstionResDto> userTranstionResDtos = new ArrayList<>();
        try {
//            List<UserTransctionHistory> userTransctionHistories = (List<UserTransctionHistory>) userTransctionHistoryDao.findAll();
            List<UserTransctionHistory> userTransctionHistories = (List<UserTransctionHistory>) userTransctionHistoryDao.getAllByBidDate(bidMasterDateReq.getBidDate());

            HashMap<String, UserTranstionResDto> uniqueNumberMap = new HashMap<>();

            for (UserTransctionHistory userTransctionHistory : userTransctionHistories) {
                String uniqueNo = userTransctionHistory.getUniqueNo();
                if (!uniqueNumberMap.containsKey(uniqueNo)) {
                    UserTranstionResDto userTranstionResDto = new UserTranstionResDto();
                    userTranstionResDto.setUniqueNo(uniqueNo);
                    userTranstionResDto.setTranStatus(userTransctionHistory.getTranStatus());
                    userTranstionResDto.setSession(userTransctionHistory.getSession());
                    userTranstionResDto.setLineName(userTransctionHistory.getLineName());
                    userTranstionResDto.setTranType(userTransctionHistory.getTranType());
                    userTranstionResDto.setDateTime(userTransctionHistory.getDateTime());
                    userTranstionResDto.setUsername(userTransctionHistory.getUserMaster().getUsername());
                    userTranstionResDto.setMobileNo(userTransctionHistory.getUserMaster().getMobileNo());
                    List<TransationResDto> transationResDtos = userTransctionHistoryDao.getValue(uniqueNo);

                    double totalPoint = 0.0;
                    for (TransationResDto transationResDto : transationResDtos) {
                        System.out.println(transationResDto.getPoints());
                        totalPoint += transationResDto.getPoints();
                        System.out.println(totalPoint);
                    }
                    userTranstionResDto.setTotalPoint(totalPoint);
                    userTranstionResDto.setTransationResDtos(transationResDtos);
                    uniqueNumberMap.put(uniqueNo, userTranstionResDto);
                    userTranstionResDtos.add(userTranstionResDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return userTranstionResDtos;
    }

    @Override
    public List getReportDayWise(DateReqDto dateReqDto) {
        List<WiningHistory> winingHistoryList = winingHistoryDao.getByDAte(dateReqDto.getBidDate());
        List<ReportWiseResDto> reportWiseResDtoList = new ArrayList<>();
        ReportWiseResDto reportWiseResDto = new ReportWiseResDto();
        List<ReportResDto> tempList = new ArrayList<>();
        Set<String> uniqueLineNames = new HashSet<>(); // To keep track of unique line names

        for (WiningHistory winingHistory : winingHistoryList) {
            String lineName = winingHistory.getLineMaster().getLineName();

            // Check if the line name has not been processed before
            if (!uniqueLineNames.contains(lineName)) {
                uniqueLineNames.add(lineName);

                ReportResDto reportResDto = new ReportResDto();
                reportResDto.setLineName(lineName);
                reportResDto.setSession(winingHistory.getSession());

                List<ReportLineWiseResDto> temp = new ArrayList<>();
                List<String> lineNames = winingHistoryDao.getLineName(winingHistory.getLineMaster().getLineId(), dateReqDto.getBidDate());

                for (String lineNameStr : lineNames) {
                    ReportLineWiseResDto reportLineWiseResDto = new ReportLineWiseResDto();
                    // Set other properties for reportLineWiseResDto
                    reportLineWiseResDto.setWinPoints(winingHistory.getWinPoints());
                    reportLineWiseResDto.setValue(winingHistory.getValue());
                    reportLineWiseResDto.setUsername(winingHistory.getUserMaster().getUsername());
                    reportLineWiseResDto.setBidPoint(winingHistory.getBidPoint());
                    temp.add(reportLineWiseResDto);
                }

                reportResDto.setReportLineWiseResDtos(temp);
                tempList.add(reportResDto);

            }
        }

        Double totalPoint = winingHistoryDao.getSumOfBidPoints(dateReqDto.getBidDate());
        reportWiseResDto.setTotalBidPoint(totalPoint);
        reportWiseResDto.setReportResDtos(tempList);
        reportWiseResDtoList.add(reportWiseResDto);

        return reportWiseResDtoList;
    }


    @Override
    public List getReportPointWise(DateReqDto dateReqDto) {

        List<ReportCreditDebitTotal> reportCreditDebitTotals = new ArrayList<>();
        List<ReportPointWiseResDto> list = new ArrayList<>(); // Changed variable name for clarity

        List<UserTransctionHistory> userTransctionHistories = userTransctionHistoryDao.getByDAte(dateReqDto.getBidDate());
        ReportCreditDebitTotal reportCreditDebitTotal = new ReportCreditDebitTotal();

        for (UserTransctionHistory userTransctionHistory : userTransctionHistories) {
            ReportPointWiseResDto reportPointWiseResDto = new ReportPointWiseResDto();
            reportPointWiseResDto.setDateTime(userTransctionHistory.getDateTime());
            reportPointWiseResDto.setUserName(userTransctionHistory.getUserMaster().getUsername());
            reportPointWiseResDto.setTranStatus(userTransctionHistory.getTranStatus());
            if (userTransctionHistory.getTranStatus().equals("Credit")) {
                reportPointWiseResDto.setCreditPoints(userTransctionHistory.getPoints());
            } else if (userTransctionHistory.getTranStatus().equals("Debit")) {
                reportPointWiseResDto.setDebitPoint(userTransctionHistory.getPoints());
            }

            list.add(reportPointWiseResDto);
        }

        Double totalPointDebit = userTransctionHistoryDao.getSumOfBidPoints(dateReqDto.getBidDate());
        Double totalPointCredit = userTransctionHistoryDao.getSumOfBidPoint(dateReqDto.getBidDate());

// Corrected setting of total credit amount
        reportCreditDebitTotal.setTotalCreditAmount(totalPointCredit);
        reportCreditDebitTotal.setTotalDebitAmount(totalPointDebit);

// Corrected adding the list to reportCreditDebitTotals
        reportCreditDebitTotal.setReportPointWiseResDtoList(list);
        reportCreditDebitTotals.add(reportCreditDebitTotal);

        return reportCreditDebitTotals;
    }


}
