package com.example.gameDemo.service.impl;

import com.example.gameDemo.payload.req.GameLogicReq;
import com.example.gameDemo.payload.res.GamelogicResDto;
import com.example.gameDemo.service.GameLogicService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameLogicServiceImpl implements GameLogicService {
    @Override
    public List allGameList(GameLogicReq gameLogicReq) {

        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        System.out.println(" publiclist size 1 = "+publiclist.size());
        if (gameLogicReq.getType().equals("default") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            GamelogicResDto defaltObject =new GamelogicResDto();
            defaltObject=defaltGame(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            publiclist.add(defaltObject);
        } else if (gameLogicReq.getType().equals("SP") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closesp")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= singlePana(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("DP") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closedp")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject=doublePanna(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("spCommon") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("ClosespCommon")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject=spCommon(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("dptCommon") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("ClosedptCommon")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= dptCommon(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("halfRedBracket") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= halfRedBracket(gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("fullRedBracket") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= fullRedBracket(gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("cp") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closecp")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= cp(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("doubleGhar") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= doubleGhar(gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        }else if (gameLogicReq.getType().equals("spMotors") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("ClosespMotor")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= spMotors(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }

        } else if (gameLogicReq.getType().equals("dpMotors") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("ClosedpMotor")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= logicToDP(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());

            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("spdpMotors") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("ClosespdpMotor")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= spdpMotors(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("jodiFamily") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= jodiFamily(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("cht30") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closecht30")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= cht30(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("cht40") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closecht40")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= cht40(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("cht50") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closecht50")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= cht50(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("cht70") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closecht70")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= cht70(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("berries") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= berries(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("otc") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= otc(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("spchaukada") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= spchaukada(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("bktBracket") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= bktBracket(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        } else if (gameLogicReq.getType().equals("dptchaukada") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("Closed")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= dptchaukada(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                publiclist.add(gamelogicResDto);
            }
        }
        else if (gameLogicReq.getType().equals("pannaFamily") && gameLogicReq.getSession().equals("Open") || gameLogicReq.getSession().equals("pannaClose")) {
            List<GamelogicResDto> defaltObject =new ArrayList<>();
            defaltObject= pannaFamily(gameLogicReq.getBidNo(), gameLogicReq.getPoint(), gameLogicReq.getSession());
            for (GamelogicResDto gamelogicResDto : defaltObject) {
                System.out.println("  gamelogicResDto =="+gamelogicResDto.toString());
                publiclist.add(gamelogicResDto);
            }
        }

        List list=new ArrayList();
        System.out.println("  publiclist  =="+ publiclist.toString());
        System.out.println(" =="+gameLogicReq.getType()+"==");

        if(gameLogicReq.getType().compareTo("default")==0||gameLogicReq.getType().compareTo("doubleGhar")==0||gameLogicReq.getType().compareTo("cht30")==0||gameLogicReq.getType().compareTo("cht40")==0||gameLogicReq.getType().compareTo("cht50")==0||gameLogicReq.getType().compareTo("cht70")==0||gameLogicReq.getType().compareTo("berries")==0||gameLogicReq.getType().compareTo("pannaFamily")==0||gameLogicReq.getType().compareTo("halfRedBracket")==0||gameLogicReq.getType().compareTo("fullRedBracket")==0||gameLogicReq.getType().compareTo("spMotors")==0||gameLogicReq.getType().compareTo("dpMotors")==0||gameLogicReq.getType().compareTo("spdpMotors")==0||gameLogicReq.getType().compareTo("SP")==0||gameLogicReq.getType().compareTo("DP")==0||gameLogicReq.getType().compareTo("cp")==0||gameLogicReq.getType().compareTo("spCommon")==0||gameLogicReq.getType().compareTo("dptCommon")==0||gameLogicReq.getType().compareTo("jodiFamily")==0|| gameLogicReq.getType().compareTo("otc")==0){
            System.out.println("  cht30 is return ");
            if(gameLogicReq.getType().compareTo("doubleGhar")==0) {
                System.out.println(" doubleGhar  is called==");
                for (GamelogicResDto gamelogicResDto : publiclist) {
                        if(Integer.parseInt(gamelogicResDto.getBidNo())==0)
                        {
                            System.out.println("  bid no == 0");
                            gamelogicResDto.setBidNo("00");
                        }
                }
            }
//            slist.addAll(publiclist);
            System.out.println("  SIZE 2 ="+list.size());
            return publiclist;
        } else if(gameLogicReq.getBidNo().contains("-"))
        {
            list=publiclist;
        }else {
            int no = Integer.parseInt(gameLogicReq.getBidNo());
            while (no != 0) {
                List list1 = new ArrayList();
                String s = String.valueOf(no % 10);
                list1 = publiclist.stream().filter(gamelogicResDto -> gamelogicResDto.getBidNo().contains(s)).collect(Collectors.toList());
                no = no / 10;
                publiclist = list1;
                list.addAll(list1);
            }
        }

        System.out.println("slist =="+list);
//        list=publiclist.stream().filter(gamelogicResDto ->  gamelogicResDto.getBidNo().contains(gameLogicReq.getBidNo())).collect(Collectors.toList());


        System.out.println("  SIZE 2 ="+list.size());
        return  list;
    }

    @Override
    public List removeObject(GameLogicReq gameLogicReq) {
        return null;
    }

    private GamelogicResDto defaltGame(String bidNo, Double point, String session) {
        System.out.println("  bidNo ="+bidNo);
        GamelogicResDto gamelogicResDto = new GamelogicResDto();
        gamelogicResDto.setPoint(point);
        gamelogicResDto.setBidNo(bidNo);
        if (bidNo.length()==1) {
           gamelogicResDto.setGameRateName("Single 1 kA");
        } else if ((bidNo.length()==2)) {
            gamelogicResDto.setGameRateName("Jodi 1 KA");
        } else if ((bidNo.length()==3)) {

            char a=bidNo.charAt(0);
            char b=bidNo.charAt(1);
            char c=bidNo.charAt(2);

            if(a==b && a==c){

                gamelogicResDto.setGameRateName("Triple Pana 1 KA");
            }
            else if(a==b)
            {
                gamelogicResDto.setGameRateName("Double Pana 1 KA");
            }
            else if(b==c){
                gamelogicResDto.setGameRateName("Double Pana 1 KA");
            }
            else {
                gamelogicResDto.setGameRateName("Single Pana 1 KA");
            }
        }
      else  if(bidNo.contains("-")){
            String c = "-";
            String r1 = null;
            String r2 = null;
            String st;
            String en;
            if(bidNo.length()>5)
            {
                r1=bidNo;
                r2=bidNo;
            }else if(bidNo.contains(c)&&bidNo.length()==5)
            {
                if(bidNo.charAt(1)=='-'){
                    st=bidNo.substring(0,1);
                    en=bidNo.substring(2,5);
                    r1=st.concat(c).concat(en);
                    r2=en.concat(c).concat(st);
                } else if (bidNo.charAt(3)=='-') {
                    st=bidNo.substring(0,3);
                    en=bidNo.substring(4,5);
                    r1=st.concat(c).concat(en);
                    r2=en.concat(c).concat(st);
                }
            }

            gamelogicResDto.setGameRateName("Full Sangam 1 Ka");
            gamelogicResDto.setSession(session);
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setBidNo(bidNo);

        }

        gamelogicResDto.setSession(session);


        return gamelogicResDto;
    }

    private List singlePana(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {
            case "0":
                int[] zeroNo = {127, 136, 145, 190, 235, 280, 370, 389, 460, 479, 569, 578};
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {128, 137, 146, 236, 245, 290, 380, 470, 489, 560, 579, 678};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {129, 138, 147, 156, 237, 246, 345, 390, 480, 570, 589, 679};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {120, 139, 148, 157, 238, 247, 256, 346, 490, 580, 670, 689};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {130, 149, 158, 167, 239, 248, 257, 347, 356, 590, 680, 789};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {140, 159, 168, 230, 249, 258, 267, 348, 357, 456, 690, 780};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "6":
                int[] sixNo = {123, 150, 169, 158, 240, 257, 268, 349, 358, 367, 457, 790};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {124, 160, 179, 250, 269, 278, 340, 359, 368, 458, 467, 890};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {125, 134, 170, 189, 260, 279, 350, 369, 378, 459, 468, 567};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {126, 135, 180, 234, 270, 289, 360, 379, 450, 469, 478, 568};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closesp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }

        return publiclist;
    }

    public List spchaukada(String bidNo, Double point, String session) {
        System.out.println("  sp chukoda called ");
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        String[] parts = bidNo.split("-");
        if (parts[0].length() == 1) {

            String[] newParts = parts[1].split("");

            for (String item : newParts) {
                String concatNew = "-" + item;
                switch (parts[0]) {
                    case "0":
                        int[] zeroNo = {127, 136, 145, 190, 235, 280, 370, 389, 460, 479, 569, 578};
                        for (int i : zeroNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                              gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "1":
                        int[] oneNo = {128, 137, 146, 236, 245, 290, 380, 470, 489, 560, 579, 678};
                        for (int i : oneNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "2":

                        int[] twoNo = {129, 138, 147, 156, 237, 246, 345, 390, 480, 570, 589, 679};
                        for (int i : twoNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "3":

                        int[] threeNo = {120, 139, 148, 157, 238, 247, 256, 346, 490, 580, 670, 689};
                        for (int i : threeNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "4":
                        int[] fourNo = {130, 149, 158, 167, 239, 248, 257, 347, 356, 590, 680, 789};
                        for (int i : fourNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "5":
                        int[] fiveNo = {140, 159, 168, 230, 249, 258, 267, 348, 357, 456, 690, 780};
                        for (int i : fiveNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "6":
                        int[] sixNo = {123, 150, 169, 158, 240, 257, 268, 349, 358, 367, 457, 790};
                        for (int i : sixNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "7":
                        int[] sevenNo = {124, 160, 179, 250, 269, 278, 340, 359, 368, 458, 467, 890};
                        for (int i : sevenNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "8":
                        int[] eightNo = {125, 134, 170, 189, 260, 279, 350, 369, 378, 459, 468, 567};
                        for (int i : eightNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "9":
                        int[] nineNo = {126, 135, 180, 234, 270, 289, 360, 379, 450, 469, 478, 568};
                        for (int i : nineNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                }
            }
        } else {
            System.out.println("end");
            String[] newParts = parts[0].split("");
            for (String item : newParts) {
                String concatNew = item + "-";
                switch (parts[1]) {
                    case "0":
                        int[] zeroNo = {127, 136, 145, 190, 235, 280, 370, 389, 460, 479, 569, 578};
                        for (int i : zeroNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "1":
                        int[] oneNo = {128, 137, 146, 236, 245, 290, 380, 470, 489, 560, 579, 678};
                        for (int i : oneNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "2":

                        int[] twoNo = {129, 138, 147, 156, 237, 246, 345, 390, 480, 570, 589, 679};
                        for (int i : twoNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "3":

                        int[] threeNo = {120, 139, 148, 157, 238, 247, 256, 346, 490, 580, 670, 689};
                        for (int i : threeNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "4":
                        int[] fourNo = {130, 149, 158, 167, 239, 248, 257, 347, 356, 590, 680, 789};
                        for (int i : fourNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "5":
                        int[] fiveNo = {140, 159, 168, 230, 249, 258, 267, 348, 357, 456, 690, 780};
                        for (int i : fiveNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "6":
                        int[] sixNo = {123, 150, 169, 158, 240, 257, 268, 349, 358, 367, 457, 790};
                        for (int i : sixNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "7":
                        int[] sevenNo = {124, 160, 179, 250, 269, 278, 340, 359, 368, 458, 467, 890};
                        for (int i : sevenNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "8":
                        int[] eightNo = {125, 134, 170, 189, 260, 279, 350, 369, 378, 459, 468, 567};
                        for (int i : eightNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "9":
                        int[] nineNo = {126, 135, 180, 234, 270, 289, 360, 379, 450, 469, 478, 568};
                        for (int i : nineNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                }
            }

        }
        return publiclist;
    }

    public List dptchaukada(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        String[] parts = bidNo.split("-");
        for (String part : parts) {
            System.out.println(" part ="+part);
        }
        if (parts[0].length() == 1) {
            System.out.println("start");
            String[] newParts = parts[1].split("");
            for (String newPart : newParts) {
                System.out.println(  " newPart " +newPart);
            }

            for (String item : newParts) {
                String concatNew = "-" + item;
                switch (parts[0]) {
                    case "0":
                        int[] zeroNo = {118, 226, 244, 299, 234, 488, 550, 668, 677, 000};
                        for (int i : zeroNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "1":
                        int[] oneNo = {100, 119, 155, 227, 335, 344, 399, 588, 669, 777};
                        for (int i : oneNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "2":

                        int[] twoNo = {110, 200, 228, 255, 236, 499, 660, 688, 778, 444};
                        for (int i : twoNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);

                            gamelogicResDto.setSession(session);
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "3":

                        int[] threeNo = {166, 229, 300, 337, 355, 445, 599, 779, 788, 111};
                        for (int i : threeNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "4":
                        int[] fourNo = {112, 220, 266, 238, 400, 446, 455, 699, 770, 888};
                        for (int i : fourNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "5":
                        int[] fiveNo = {113, 112, 177, 339, 366, 447, 500, 799, 889, 555};
                        for (int i : fiveNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "6":
                        int[] sixNo = {114, 277, 330, 448, 466, 556, 600, 880, 899, 222};

                        for (int i : sixNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "7":
                        int[] sevenNo = {115, 133, 188, 223, 377, 449, 557, 700, 999};
                        for (int i : sevenNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "8":
                        int[] eightNo = {116, 224, 233, 288, 440, 477, 558, 800, 990, 666};
                        for (int i : eightNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "9":
                        int[] nineNo = {117, 144, 199, 255, 388, 559, 577, 667, 900, 333};
                        for (int i : nineNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(Integer.toString(i).concat(concatNew));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                }
            }
        } else {
            System.out.println("end");
            String[] newParts = parts[0].split("");
            for (String item : newParts) {
                String concatNew = item + "-";
                switch (parts[1]) {
                    case "0":
                        int[] zeroNo = {118, 226, 244, 299, 234, 488, 550, 668, 677, 000};
                        for (int i : zeroNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "1":
                        int[] oneNo = {100, 119, 155, 227, 335, 344, 399, 588, 669, 777};
                        for (int i : oneNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "2":

                        int[] twoNo = {110, 200, 228, 255, 236, 499, 660, 688, 778, 444};
                        for (int i : twoNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "3":

                        int[] threeNo = {166, 229, 300, 337, 355, 445, 599, 779, 788, 111};
                        for (int i : threeNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "4":
                        int[] fourNo = {112, 220, 266, 238, 400, 446, 455, 699, 770, 888};
                        for (int i : fourNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "5":
                        int[] fiveNo = {113, 112, 177, 339, 366, 447, 500, 799, 889, 555};
                        for (int i : fiveNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "6":
                        int[] sixNo = {114, 277, 330, 448, 466, 556, 600, 880, 899, 222};
                        for (int i : sixNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "7":
                        int[] sevenNo = {115, 133, 188, 223, 377, 449, 557, 700, 999};
                        for (int i : sevenNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "8":
                        int[] eightNo = {116, 224, 233, 288, 440, 477, 558, 800, 990, 666};
                        for (int i : eightNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                    case "9":
                        int[] nineNo = {117, 144, 199, 255, 388, 559, 577, 667, 900, 333};
                        for (int i : nineNo) {
                            GamelogicResDto gamelogicResDto = new GamelogicResDto();
                            gamelogicResDto.setBidNo(concatNew.concat(Integer.toString(i)));
                            gamelogicResDto.setPoint(point);
                            gamelogicResDto.setSession(session);
                            if (bidNo.contains("-")) {
                                gamelogicResDto.setGameRateName("Half Sangam 1 KA");
                            }
                            publiclist.add(gamelogicResDto);
                        }
                        break;
                }
            }

        }
        return publiclist;
    }

    private List doublePanna(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);

        switch (bidNo) {
            case "0":
                int[] zeroNo = {118, 226, 244, 299, 234, 488, 550, 668, 677, 000};
                for (int i : zeroNo) {
                    String num = String.format("%03d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
////                    char b=bidNo.charAt(1);
////                    char c=bidNo.charAt(2);

//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {100, 119, 155, 227, 335, 344, 399, 588, 669, 777};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {110, 200, 228, 255, 236, 499, 660, 688, 778, 444};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);

//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }

                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {166, 229, 300, 337, 355, 445, 599, 779, 788, 111};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }

                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {112, 220, 266, 238, 400, 446, 455, 699, 770, 888};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {113, 112, 177, 339, 366, 447, 500, 799, 889, 555};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));

                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);

                }
                break;
            case "6":
                int[] sixNo = {114, 277, 330, 448, 466, 556, 600, 880, 899, 222};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {115, 133, 188, 223, 377, 449, 557, 700, 999};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {116, 224, 233, 288, 440, 477, 558, 800, 990, 666};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {117, 144, 199, 255, 388, 559, 577, 667, 900, 333};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
//                    char a=bidNo.charAt(0);
//                    char b=bidNo.charAt(1);
//                    char c=bidNo.charAt(2);
//
//                    if(a==b && a==c){
//                        gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                    }
//                    else if(a==b)
//                    {
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else if(b==c){
//                        gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                    }
//                    else {
//                        gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                    }
                    if(session.equals("Closedp")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }

        return publiclist;
    }

    private List spCommon(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {
            case "0":
                int[] zeroNo = {290, 380, 470, 560, 390, 480, 570, 120, 490, 580, 670, 130, 590, 680, 140, 230, 690, 780, 150, 240, 790, 160, 250, 340, 890, 170, 260, 350, 180, 270, 360, 450, 190, 280, 370, 460};
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {128, 137, 146, 129, 138, 147, 156, 120, 139, 148, 157, 130, 149, 158, 167, 140, 159, 168, 123, 150, 169, 178, 124, 160, 179, 125, 134, 170, 189, 126, 135, 180, 127, 136, 145, 190};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {128, 236, 245, 290, 129, 237, 246, 120, 238, 247, 256, 239, 248, 257, 230, 249, 258, 267, 123, 240, 259, 268, 124, 250, 269, 278, 125, 260, 279, 126, 234, 270, 289, 127, 280, 235};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {137, 236, 380, 138, 237, 345, 390, 139, 238, 346, 130, 239, 347, 356, 230, 348, 357, 123, 349, 358, 367, 340, 359, 368, 134, 350, 369, 378, 135, 234, 360, 379, 136, 235, 370, 389};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {146, 246, 470, 489, 147, 246, 345, 480, 148, 247, 346, 490, 149, 248, 347, 140, 249, 348, 456, 240, 349, 457, 124, 340, 458, 467, 134, 459, 468, 234, 450, 469, 478, 145, 460, 479};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {245, 560, 579, 156, 346, 570, 589, 157, 256, 580, 158, 257, 356, 590, 159, 258, 357, 456, 150, 259, 358, 457, 250, 359, 458, 125, 350, 459, 567, 135, 450, 568, 145, 235, 569, 578};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "6":
                int[] sixNo = {146, 236, 560, 678, 156, 246, 679, 256, 346, 670, 689, 167, 356, 680, 168, 267, 456, 690, 169, 168, 367, 160, 269, 368, 467, 260, 369, 468, 567, 126, 360, 469, 568, 136, 460, 569};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {137, 470, 579, 678, 147, 237, 570, 679, 157, 247, 670, 167, 257, 347, 789, 267, 357, 780, 178, 367, 457, 790, 179, 278, 467, 170, 279, 378, 567, 270, 379, 478, 127, 370, 479, 578};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {128, 380, 489, 678, 138, 480, 589, 148, 238, 580, 689, 158, 248, 680, 789, 168, 258, 348, 780, 178, 268, 358, 278, 368, 458, 890, 189, 378, 468, 180, 289, 478, 568, 280, 389, 578};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {290, 489, 579, 129, 390, 589, 679, 139, 490, 689, 149, 239, 590, 789, 159, 249, 690, 169, 259, 349, 790, 179, 269, 359, 890, 189, 279, 369, 459, 289, 379, 469, 190, 389, 479, 569};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosespCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);

                }
                break;
        }

        return publiclist;
    }

    private List dptCommon(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {
            case "0":
                int[] zeroNo = {100, 110, 200, 660, 300, 220, 400, 770, 500, 330, 600, 880, 700, 440, 800, 990, 900, 550, 000};
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {100, 119, 155, 110, 166, 111, 112, 113, 122, 177, 114, 115, 133, 188, 116, 117, 144, 199, 118};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {227, 228, 255, 200, 229, 112, 220, 266, 122, 277, 222, 223, 224, 233, 288, 225, 226, 244, 299};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {335, 344, 399, 336, 337, 355, 300, 338, 113, 339, 366, 330, 133, 223, 377, 233, 388, 333, 334};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(bidNo);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {344, 499, 444, 445, 446, 455, 400, 447, 114, 448, 466, 449, 224, 440, 477, 144, 244, 334, 488};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {155, 335, 588, 255, 355, 445, 599, 455, 500, 555, 556, 115, 557, 566, 558, 225, 559, 577, 550};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "6":
                int[] sixNo = {669, 336, 660, 688, 166, 266, 446, 699, 366, 466, 556, 600, 566, 116, 666, 667, 226, 668, 677};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {227, 777, 778, 337, 779, 788, 770, 177, 447, 799, 277, 377, 557, 700, 477, 117, 577, 667, 677};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {588, 228, 688, 778, 788, 338, 888, 889, 448, 880, 899, 188, 288, 558, 800, 388, 118, 488, 668};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {119, 399, 669, 499, 229, 599, 779, 699, 339, 799, 889, 899, 449, 999, 990, 199, 559, 900, 299};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Double Pana 1 kA");
                    if(session.equals("ClosedptCommon")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }
        return publiclist;

    }


    private List berries(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {
            case "0":
                int[] zeroNo = {00, 55, 19, 91, 28, 82, 37, 73, 64, 46};
                for (int i : zeroNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    gamelogicResDto.setSession(session);
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {29, 92, 38, 83, 47, 74, 10, 01, 65, 56};
                for (int i : oneNo) {
                    System.out.println("i"+i);
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {39, 93, 48, 84, 57, 75, 66, 11, 20, 02};
                for (int i : twoNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {30, 03, 49, 94, 67, 76, 58, 85, 21, 12};
                for (int i : threeNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    gamelogicResDto.setPoint(point);
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {40, 04, 59, 95, 13, 31, 68, 86, 22, 77};
                for (int i : fourNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    gamelogicResDto.setSession(session);
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {50, 05, 14, 41, 78, 87, 69, 96, 23, 32};
                for (int i : fiveNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "6":
                int[] sixNo = {60, 06, 15, 51, 88, 33, 79, 97, 24, 42};
                for (int i : sixNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {70, 07, 25, 52, 16, 61, 34, 43, 89, 98};
                for (int i : sevenNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {80, 8, 17, 71, 26, 62, 99, 44, 53, 35};
                for (int i : eightNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {90, 9, 18, 81, 27, 72, 45, 54, 63, 36};
                for (int i : nineNo) {
                    String num = String.format("%02d", i);
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(num);
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setSession(session);
                    gamelogicResDto.setGameRateName("Jodi 1 kA");
                    publiclist.add(gamelogicResDto);
                }
                break;
        }
        return publiclist;

    }

    private List doubleGhar(Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);

        int[] doubleGhar = {11, 22, 33, 44, 55, 66, 77, 88, 99, 00};
        for (int i : doubleGhar) {
            String num = String.format("%02d", i);
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setBidNo(num);
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setGameRateName("Jodi 1 kA");
            gamelogicResDto.setSession(session);
            publiclist.add(gamelogicResDto);
        }
        return publiclist;

    }

    private List halfRedBracket(Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);

        int[] doubleGhar = {16, 27, 38, 49, 50, 61, 72, 83, 94, 05};
        for (int i : doubleGhar) {
            String num = String.format("%02d", i);
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setBidNo(num);
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setSession(session);
            gamelogicResDto.setGameRateName("Jodi 1 kA");
            publiclist.add(gamelogicResDto);
        }
        return publiclist;


    }

    private List fullRedBracket(Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);

        int[] doubleGhar = {11, 22, 33, 44, 55, 66, 77, 88, 99, 00};
        for (int i : doubleGhar) {
            String num = String.format("%02d", i);
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setBidNo(num);
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setGameRateName("Jodi 1 kA");
            gamelogicResDto.setSession(session);
            publiclist.add(gamelogicResDto);
        }
        return publiclist;

    }

    private List cp(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
//        int[] cpMotors = {110,123, 124, 125, 126, 127, 128, 129, 120, 134, 135, 136, 137, 138, 139, 130, 145, 146, 147, 148, 149, 140, 156, 157, 158, 159, 150, 167, 168, 169, 160, 178, 179, 180, 190, 234, 235, 236, 237, 238, 239, 230, 245, 246, 247, 248, 249, 240, 256, 257, 258, 259, 250, 267, 268, 269, 260, 278, 279, 270, 289, 280, 290, 345, 346, 347, 348, 349, 340, 356, 357, 358, 389, 380, 390, 456, 457, 458, 459, 450, 467, 468, 469, 460, 478, 479, 470, 489, 480, 490, 567, 568, 569, 560, 578, 579, 570, 589, 580, 590, 678, 679, 670, 689, 680, 690, 789, 780, 790, 890,000,100,200,300,400,500,600,700,800,900};
        publiclist.removeAll(publiclist);
        int[] cpMotors =
                {111,116, 166, 666,112, 117, 126, 167, 266, 667,
                        113, 118, 136, 168, 366, 668,114, 119, 146,
                        169, 466,669,110, 115, 156, 160, 566, 660,122, 127,
                        177, 226,267, 677,123, 128, 137, 178, 236, 268, 367, 678,124,
                        129, 147, 179, 246, 269, 467, 679,120, 125, 157, 170,
                        256, 260, 567, 670,133, 138, 188, 336, 368, 688,
                        134, 139, 148, 189, 346, 369, 468, 689,130, 135,
                        158, 180, 356, 360, 568, 680,144, 149, 199, 446,
                        469, 699,140, 145, 159, 190, 456, 460, 569, 690,100,
                        150, 155, 556, 560, 600,222, 227, 277, 777,223, 228, 237, 378,
                        377, 778,224, 229, 247, 279, 477, 779,220, 225, 257, 270, 577,
                        770,233, 238, 288, 337, 378, 788,234, 239, 248,
                        289, 347, 379, 478, 789,230, 235, 258, 280, 357,
                        70, 578, 780,244, 249, 299, 447, 479, 799,240, 245, 259,
                        290, 457, 470, 579, 790,200, 250, 255, 257, 270, 700,
                        333, 338, 388, 888,334, 339, 348, 389, 488, 889,330, 335,
                        358, 380, 588, 880,344, 349, 399, 448, 489, 899,340, 345,
                        359, 390, 458, 480, 589, 890,300, 350, 355, 558, 580, 800,
                        444, 449, 499, 999,440, 445, 459, 490, 599, 990,400, 450,
                        455, 559, 590, 900,000, 500, 550, 555};

        for (int i = 0; i < cpMotors.length; i++) {
            GamelogicResDto gamelogicResDto = new GamelogicResDto();

            String s = Integer.toString(cpMotors[i]);
            int a = Integer.parseInt(s);

            String f1= String.valueOf(bidNo.charAt(0));
            String f2= String.valueOf(bidNo.charAt(1));

            System.out.println(" f1="+f1+" f2 ="+f2);

            int no1=cpMotors[i]/10;
            int no2=cpMotors[i]/10;
            int no3=cpMotors[i];
            Boolean flag=true;

//            if(s.contains(f1)&&(s.contains(f2)) )


            if(f1.compareTo(f2)==0) {
                System.out.println(" f1="+f1);

               switch (Integer.parseInt(f1))
               {
                   case 0:
                       System.out.println("  0 is called ");
                       int[] zero={000,100,200,300,400,500,600,700,800,900};
                       for (int i1 : zero) {
                           String num = String.format("%03d", i1);
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(num);
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 1:
                       System.out.println("  1 is called ");
                       int[] one={110,111,112,113,114,115,116,117,118,119};
                       for (int i1 : one) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 2:
                       System.out.println("  2 is called ");
                       int[] two={122,220,223,224,225,226,227,228,229,222};
                       for (int i1 : two) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 3:
                       int[] three={};
                       break;
                   case 4:
                       System.out.println("  4 is called ");
                       int[] four={144,244,344,440,449,445,446,447,448,444};
                       for (int i1 : four) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 5:
                       System.out.println("  5 is called ");
                       int[] five={155,556,557,558,559,255,355,455,555,550};
                       for (int i1 : five) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 6:
                       System.out.println("  6 is called ");
                       int[] six={660,667,668,669,666,166,266,366,466,566};
                       for (int i1 : six) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 7:
                       System.out.println("  7 is called ");
                       int[] seven={770,177,277,377,477,577,677,778,779,777};
                       for (int i1 : seven) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 8:
                       int[] eight={188,288,388,488,588,688,788,889,888,880};
                       for (int i1 : eight) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;
                   case 9:
                       int[] nine={199,299,399,499,599,699,799,899,990,999};
                       for (int i1 : nine) {
                           GamelogicResDto gamelogicResDto1 = new GamelogicResDto();
                           gamelogicResDto1.setPoint(point);
                           gamelogicResDto1.setBidNo(String.valueOf(i1));
                           if (session.equals("Closecp")) {
                               gamelogicResDto1.setSession("Close");
                           } else if (session.equals("Open")) {
                               gamelogicResDto1.setSession(session);
                           }
                           gamelogicResDto1.setGameRateName("Double Pana 1 KA");
                           publiclist.add(gamelogicResDto1);
                       }
                       break;

               }

               return  publiclist;
//                for (int j=0;j<s.length();j++)
//                {
//                    System.out.print(" "+s.charAt(j));
//                }
//                System.out.println();

            }else if(s.contains(f1)&&(s.contains(f2))) {
                System.out.println("  s=="+s);
                        gamelogicResDto.setPoint(point);
                        gamelogicResDto.setBidNo(s);
                        if (session.equals("Closecp")) {
                            gamelogicResDto.setSession("Close");
                        } else if (session.equals("Open")) {
                            gamelogicResDto.setSession(session);
                        }
//                char aa=bidNo.charAt(0);
//                char b=bidNo.charAt(1);
//                char c=bidNo.charAt(2);

//                if(aa==b && aa==c){
//                    gamelogicResDto.setGameRateName("Triple Pana 1 KA");
//                }
//                else if(aa==b)
//                {
//                    gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                }
//                else if(b==c){
//                    gamelogicResDto.setGameRateName("Double Pana 1 KA");
//                }
//                else {
//                    gamelogicResDto.setGameRateName("Single Pana 1 KA");
//                }


                        gamelogicResDto.setGameRateName("Double Pana 1 KA");

                        if(publiclist.contains(gamelogicResDto))
                        {
                            System.out.println("  Already Exist ");
                        }else {
                            publiclist.add(gamelogicResDto);
                        }
            }
        }
        return publiclist;
    }

    private List spMotors(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        int r = 3;
        List<String> combinations = generateCombinations(bidNo, r);
        for (String combination : combinations) {
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setBidNo(combination);
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setGameRateName("Single 1 kA");
            if(session.equals("ClosespMotor")){
                gamelogicResDto.setSession("Close");
            }
            else if(session.equals("Open")){
                gamelogicResDto.setSession(session);
            }
            publiclist.add(gamelogicResDto);
        }
        return publiclist;
    }

    public static List<String> generateCombinations(String s, int r) {
        List<String> result = new ArrayList<>();
        generateCombinationsHelper(s, r, 0, new StringBuilder(), result);
        return result;
    }

    private static void generateCombinationsHelper(String s, int r, int index, StringBuilder current, List<String> result) {
        if (current.length() == r) {
            result.add(current.toString());
            return;
        }

        for (int i = index; i < s.length(); i++) {
            current.append(s.charAt(i));
            generateCombinationsHelper(s, r, i + 1, current, result);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }


    ArrayList<String> matchString = new ArrayList<>();
    List<String> stringBufferList = new ArrayList<>();

    private List logicToDP(String bidNo, Double point, String session) {

        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);

        bidNo.length();
        for (int i = 0; i < bidNo.length(); i++) {

            bidNo.charAt(i);
            bidNo.charAt(bidNo.length() - 1);
            if (i == bidNo.length() - 1) {
            } else {

                for (int j = i; j < bidNo.length(); j++) {

                    if (j == bidNo.length() - 1) {

                    } else {
                        giveFirstLast(bidNo.charAt(i), bidNo.charAt(j + 1));
                    }
                }
            }
        }

        System.out.println("matchString size="+matchString.size());
        for (int k = 0; k < matchString.size(); k++) {
            System.out.println("aaa" + matchString.get(k));
            String bidNo1=String.valueOf(Integer.valueOf(matchString.get(k)));
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setBidNo(String.valueOf(Integer.valueOf(matchString.get(k))));
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setGameRateName("Double Pana 1 kA");

            if(session.equals("ClosedpMotor")){
                gamelogicResDto.setSession("Close");
            }
            else if(session.equals("Open")){
                gamelogicResDto.setSession(session);
            }

            boolean allPresent=true;
            for (char c : bidNo1.toCharArray()) {
                if (bidNo.indexOf(c) == -1) {
                    allPresent = false;
                    break;
                }
            }
            if(allPresent) {
                if(!publiclist.stream().anyMatch(gamelogicResDto1 -> bidNo1.equals(gamelogicResDto1.getBidNo()))) {
                    if(gamelogicResDto.getBidNo()!=null) {
                        publiclist.add(gamelogicResDto);
                    }
                }
//                publiclist.add(gamelogicResDto);
//                System.out.println(" bidno 123 === "+gamelogicResDto.getBidNo());
            }

        }

        return publiclist;

    }

    private void giveFirstLast(char charAt, char charAt1) {
        ArrayList<String> stringArrayList = getDoublePana();

        System.out.println(" charAt 1 == "+charAt);
        System.out.println(" charAt 2 == "+charAt1);
        for (int j = 0; j < stringArrayList.size(); j++) {
            if (stringArrayList.get(j).startsWith(String.valueOf(charAt)) && stringArrayList.get(j).endsWith(String.valueOf(charAt1))) {
                System.out.println("  NO =="+stringArrayList.get(j));
                matchString.add(stringArrayList.get(j));
            }
        }
    }

    private ArrayList<String> getDoublePana() {
        ArrayList<String> doublePana = new ArrayList<>();
        doublePana.add("112");
        doublePana.add("113");
        doublePana.add("114");
        doublePana.add("115");
        doublePana.add("116");
        doublePana.add("117");
        doublePana.add("118");
        doublePana.add("119");
        doublePana.add("110");

        doublePana.add("100");
        doublePana.add("122");
        doublePana.add("133");
        doublePana.add("144");
        doublePana.add("155");
        doublePana.add("166");
        doublePana.add("177");
        doublePana.add("188");
        doublePana.add("199");

        doublePana.add("223");
        doublePana.add("224");
        doublePana.add("225");
        doublePana.add("226");
        doublePana.add("227");
        doublePana.add("228");
        doublePana.add("229");
        doublePana.add("220");

        doublePana.add("200");
        doublePana.add("233");
        doublePana.add("244");
        doublePana.add("255");
        doublePana.add("266");
        doublePana.add("277");
        doublePana.add("288");
        doublePana.add("299");


        doublePana.add("334");
        doublePana.add("335");
        doublePana.add("336");
        doublePana.add("337");
        doublePana.add("338");
        doublePana.add("339");
        doublePana.add("330");

        doublePana.add("300");
        doublePana.add("344");
        doublePana.add("355");
        doublePana.add("366");
        doublePana.add("377");
        doublePana.add("388");
        doublePana.add("399");

        doublePana.add("445");
        doublePana.add("446");
        doublePana.add("447");
        doublePana.add("448");
        doublePana.add("449");
        doublePana.add("440");

        doublePana.add("400");

        doublePana.add("455");
        doublePana.add("466");
        doublePana.add("477");
        doublePana.add("488");
        doublePana.add("499");

        doublePana.add("556");
        doublePana.add("557");
        doublePana.add("558");
        doublePana.add("559");
        doublePana.add("550");

        doublePana.add("500");

        doublePana.add("566");
        doublePana.add("577");
        doublePana.add("588");
        doublePana.add("599");

        doublePana.add("667");
        doublePana.add("668");
        doublePana.add("669");
        doublePana.add("660");

        doublePana.add("600");
        doublePana.add("677");
        doublePana.add("688");
        doublePana.add("699");


        doublePana.add("778");
        doublePana.add("779");
        doublePana.add("770");
        doublePana.add("700");

        doublePana.add("788");
        doublePana.add("799");

        doublePana.add("889");
        doublePana.add("880");
        doublePana.add("800");
        doublePana.add("899");

        doublePana.add("990");
        doublePana.add("900");

        return doublePana;
    }

    void printCombination(int arr[], int n, int r) {
        // A temporary array to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, data, 0, n - 1, 0, r);
    }

    void combinationUtil(int arr[], int data[], int start,
                         int end, int index, int r) {
        if (index == r) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < r; j++) {
                System.out.print(data[j] + " ");
                stringBuilder.append(data[j]);

            }
            stringBufferList.add(stringBuilder.toString());
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r);
        }
    }


    public List spdpMotors(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        System.out.println(" publiclist size in spdp  = "+publiclist.size());
        publiclist.removeAll(publiclist);
        List list = new ArrayList();
        bidNo.length();

        int r = 3;
        for (int i = 0; i < bidNo.length(); i++) {

            bidNo.charAt(i);
            bidNo.charAt(bidNo.length() - 1);
            if (i == bidNo.length() - 1) {
            } else {

                for (int j = i; j < bidNo.length(); j++) {
                    if (j == bidNo.length() - 1) {
                    } else {
                        giveFirstLast(bidNo.charAt(i), bidNo.charAt(j + 1));

                    }
                }
            }
        }



        List<String> combinations = new ArrayList<>();
        combinations=generateCombinations(bidNo, r);
        System.out.println("  combinationsize =="+combinations.size());
        for (String combination : combinations) {
            System.out.println( "  combinations ======");
            System.out.println( " Bid No = "+String.valueOf(Integer.valueOf(combination)));
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setBidNo(String.valueOf(Integer.valueOf(combination)));
            gamelogicResDto.setPoint(point);
            if(session.equals("ClosespdpMotor")){
                gamelogicResDto.setSession("Close");
            }
            else if(session.equals("Open")){
                gamelogicResDto.setSession(session);
            }
            char aa=bidNo.charAt(0);
            char b=bidNo.charAt(1);
            char c=bidNo.charAt(2);

            if(aa==b && aa==c){
                gamelogicResDto.setGameRateName("Triple Pana 1 KA");
            }
            else if(aa==b)
            {
                gamelogicResDto.setGameRateName("Double Pana 1 KA");
            }
            else if(b==c){
                gamelogicResDto.setGameRateName("Double Pana 1 KA");
            }
            else {
                gamelogicResDto.setGameRateName("Single Pana 1 KA");
            }

//            publiclist.add(gamelogicResDto);
            if(!publiclist.stream().anyMatch(gamelogicResDto1 -> String.valueOf(Integer.valueOf(combination)).equals(gamelogicResDto1.getBidNo()))) {
                publiclist.add(gamelogicResDto);
            }


        }

        System.out.println("matchString size="+matchString.size());


        for (int k = 0; k < matchString.size(); k++) {
            System.out.println( "  Matching String  ======");
            System.out.println( " Bid No = "+String.valueOf(Integer.valueOf(matchString.get(k))));
            System.out.println("aaa" + matchString.get(k));
            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            String bidNo1=String.valueOf(Integer.valueOf(matchString.get(k)));

            boolean allPresent=true;
            for (char c : bidNo1.toCharArray()) {
                if (bidNo.indexOf(c) == -1) {
                    allPresent = false;
                    break;
                }
            }
            if(allPresent) {
                gamelogicResDto.setBidNo(String.valueOf(Integer.valueOf(matchString.get(k))));
                gamelogicResDto.setPoint(point);
                System.out.println(" bidno 123 === "+gamelogicResDto.getBidNo());
            }
            if(session.equals("ClosespdpMotor")){
                gamelogicResDto.setSession("Close");
            }
            else if(session.equals("Open")){
                gamelogicResDto.setSession(session);
            }

            char aa=bidNo.charAt(0);
            char b=bidNo.charAt(1);
            char c=bidNo.charAt(2);

            if(aa==b && aa==c){
                gamelogicResDto.setGameRateName("Triple Pana 1 KA");
            }
            else if(aa==b)
            {
                gamelogicResDto.setGameRateName("Double Pana 1 KA");
            }
            else if(b==c){
                gamelogicResDto.setGameRateName("Double Pana 1 KA");
            }
            else {
                gamelogicResDto.setGameRateName("Single Pana 1 KA");
            }
//             publiclist.add(gamelogicResDto);

            if(!publiclist.stream().anyMatch(gamelogicResDto1 -> bidNo1.equals(gamelogicResDto1.getBidNo()))) {

               if(gamelogicResDto.getBidNo()!=null) {
                   publiclist.add(gamelogicResDto);
               }
            }
        }


        System.out.println(" publiclist size in spdp 2  = "+publiclist.size());
        return publiclist;
    }

    public List jodiFamily(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        System.out.println("  jodiFamily is called ");
        List<int[]> arrayList = null;
        arrayList = new ArrayList();
        switch (bidNo) {
            case "11":
            case "66":
            case "16":
            case "61":
                System.out.println(" 11 is called ");
                arrayList = Collections.singletonList(new int[]{11, 66, 16, 61});
                break;
            case "22":
            case "77":
            case "27":
            case "72":
                arrayList = Collections.singletonList(new int[]{22, 77, 72, 27});
                break;
            case "33":
            case "88":
            case "38":
            case "83":
                arrayList = Collections.singletonList(new int[]{33, 88, 38, 83});
                break;
            case "44":
            case "99":
            case "94":
            case "49":
                arrayList = Collections.singletonList(new int[]{44, 99, 49, 94});
                break;
            case "55":
            case "00":
            case "50":
            case "05":
                arrayList = Collections.singletonList(new int[]{55, 0, 50, 5});
                break;
            case "12":
            case "17":
            case "21":
            case "26":
            case "62":
            case "67":
            case "71":
            case "76":
                System.out.println("  12 is called" );
                arrayList = Collections.singletonList(new int[]{12, 17, 21, 26,62, 67, 71, 76});
                break;
            case "13":
            case "18":
            case "31":
            case "36":
            case "63":
            case "68":
            case "81":
            case "86":
                arrayList = Collections.singletonList(new int[]{13,18,31,36,63,68,81,86});
                break;
            case "14":
            case "19":
            case "41":
            case "46":
            case "64":
            case "69":
            case "91":
            case "96":
                arrayList = Collections.singletonList(new int[]{14 ,19 ,41 ,46 ,64 ,69 ,91 ,96});
                break;
            case "01":
            case "06":
            case "10":
            case "15":
            case "51":
            case "56":
            case "60":
            case "65":
                arrayList = Collections.singletonList(new int[]{1 ,6 ,10 ,15 ,51 ,56 ,60 ,65});
                break;
            case "23":
            case "28":
            case "32":
            case "37":
            case "73":
            case "78":
            case "82":
            case "87":
                arrayList = Collections.singletonList(new int[]{23 ,28 ,32 ,37 ,73 ,78 ,82 ,87});
                break;
            case "24":
            case "29":
            case "42":
            case "47":
            case "74":
            case "79":
            case "92":
            case "97":
                arrayList = Collections.singletonList(new int[]{24 ,29 ,42 ,47 ,74 ,79 ,92 ,97});
                break;
            case "02":
            case "07":
            case "20":
            case "25":
            case "52":
            case "57":
            case "70":
            case "75":
                arrayList = Collections.singletonList(new int[]{02 ,07, 20, 25, 52, 57, 70, 75});
                break;
            case "34":
            case "39":
            case "43":
            case "48":
            case "84":
            case "89":
            case "93":
            case "98":
                arrayList = Collections.singletonList(new int[]{34 ,39 ,43, 48, 84, 89, 93, 98});
                break;
            case "03":
            case "08":
            case "30":
            case "35":
            case "53":
            case "58":
            case "80":
            case "85":
                arrayList = Collections.singletonList(new int[]{3, 30, 35, 53, 58, 80, 85,8});
                break;
            case "04":
            case "09":
            case "40":
            case "45":
            case "54":
            case "59":
            case "90":
            case "95":
                arrayList = Collections.singletonList(new int[]{4, 9, 40, 45, 54 ,59, 90 ,95});
                break;
        }


//        int[][] testArr = {
//                {0, 5},
//                {1, 6},
//                {2, 7},
//                {3, 8},
//                {4, 9}
//        };
//
//
//        int num1 = Integer.parseInt(bidNo) / 10;
//        int num2 = Integer.parseInt(bidNo) % 10;
//
//        int[] arr1 = null;
//        int[] arr2 = null;
//
//        for (int[] arr : testArr) {
//            if (contains(arr, num1)) {
//                arr1 = arr;
//            } else if (contains(arr, num2)) {
//                arr2 = arr;
//            }
//        }
//
        List<int[]> newArray = new ArrayList<>();
        newArray.addAll(arrayList);
//
//        if (arr1 != null && arr2 != null) {
//            for (int i : arr1) {
//                for (int j : arr2) {
//                    int ap = i * 10 + j;
//                    newArray.add(ap);
//                    int newAp = j * 10 + i;
//                    newArray.add(newAp);
//                }
//            }
//        } else if (arr1 != null) {
//            int index = 0;
//            for (int i : arr1) {
//
//                int ap = i * 10 + i;
//                newArray.add(ap);
//                if (index == 0) {
//                    String first = Integer.toString(arr1[0]) + Integer.toString(arr1[1]);
//                    newArray.add(Integer.parseInt(first));
//                    index++;
//                } else {
//                    String first = Integer.toString(arr1[1]) + Integer.toString(arr1[0]);
//                    newArray.add(Integer.parseInt(first));
//                }
//            }
//
//        }

//        for (int i = 0; i <= newArray.size(); i++) {

        for(int[] ints : arrayList){
            for (int i : ints) {
//            for (int i = 0; i < newArray.size(); i++) {
                System.out.println(" i no =" + i);

                String num = String.format("%02d", i);
                GamelogicResDto gamelogicResDto = new GamelogicResDto();
                gamelogicResDto.setSession(session);
                gamelogicResDto.setGameRateName("Jodi 1 kA");
                gamelogicResDto.setPoint(point);
//            gamelogicResDto.setBidNo(String.valueOf(newArray.get(i)));
                gamelogicResDto.setBidNo(num);
                publiclist.add(gamelogicResDto);
            }
        }
        return publiclist;

    }

    private static boolean contains(int[] arr, int num) {
        return Arrays.stream(arr).anyMatch(n -> n == num);
    }

    public List cht30(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        String numberStr = Integer.toString(Integer.parseInt(bidNo));

        switch (numberStr) {
            case "0":
                int[] zeroNo = {136, 370, 479};
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {146, 380, 470};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {138, 147, 570};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {148, 247, 580};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {149, 158, 257};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {168, 249, 258};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    gamelogicResDto.setPoint(point);
                    publiclist.add(gamelogicResDto);

                }
                break;
            case "6":
                int[] sixNo = {169, 259, 358};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {250, 269, 368};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {279, 350, 369};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {270, 360, 469};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht30")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }
        return publiclist;

    }

    public List cht40(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {
            case "0":
                int[] zeroNo = {145, 235, 569, 578};
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {128, 236, 245, 290};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {129, 390, 589, 679};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {256, 146, 670, 689};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {130, 239, 347, 356};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {140, 230, 690, 780};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    gamelogicResDto.setPoint(point);
                    publiclist.add(gamelogicResDto);

                }
                break;
            case "6":
                int[] sixNo = {178, 367, 457, 790};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {124, 340, 458, 467};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {125, 134, 170, 189};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {180, 289, 478, 568};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht40")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }

        return publiclist;
    }

    public List cht50(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {

            case "0":
                int[] zeroNo = {136, 280, 370, 460, 479};
                System.out.println("  zero is called ");
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {137,146, 380, 470, 579};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {147, 246, 480, 570, 138};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {139, 148, 157, 247, 580};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {149, 158, 248, 257, 680};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {159, 168, 249, 258, 357};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    gamelogicResDto.setPoint(point);
                    publiclist.add(gamelogicResDto);

                }
                break;
            case "6":
                int[] sixNo = {169, 240, 259, 268, 358};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    gamelogicResDto.setPoint(point);
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {179, 250, 269, 359, 368};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {260, 279, 350, 369, 469};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {135, 270, 360, 379, 469};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht50")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }
        return publiclist;

    }

    public List cht70(String bidNo, Double point, String session) {
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        switch (bidNo) {
            case "0":
                int[] zeroNo = {127, 145, 190, 235, 389, 569, 578};
                for (int i : zeroNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "1":
                int[] oneNo = {128, 236, 245, 290, 489, 560, 678};
                for (int i : oneNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "2":

                int[] twoNo = {129, 156, 237, 345, 390, 589, 679};
                for (int i : twoNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "3":

                int[] threeNo = {120, 238, 256, 346, 490, 670, 689};
                for (int i : threeNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "4":
                int[] fourNo = {130, 167, 239, 347, 356, 590, 789};
                for (int i : fourNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "5":
                int[] fiveNo = {140, 230, 267, 348, 456, 690, 785};
                for (int i : fiveNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    gamelogicResDto.setPoint(point);
                    publiclist.add(gamelogicResDto);

                }
                break;
            case "6":
                int[] sixNo = {123, 150, 178, 349, 367, 457, 790};
                for (int i : sixNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "7":
                int[] sevenNo = {124, 160, 278, 340, 458, 467, 890};
                for (int i : sevenNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "8":
                int[] eightNo = {125, 134, 170, 189, 378, 459, 567};
                for (int i : eightNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
            case "9":
                int[] nineNo = {126, 180, 234, 289, 450, 478, 568};
                for (int i : nineNo) {
                    GamelogicResDto gamelogicResDto = new GamelogicResDto();
                    gamelogicResDto.setBidNo(Integer.toString(i));
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setGameRateName("Single Pana 1 kA");
                    if(session.equals("Closecht70")){
                        gamelogicResDto.setSession("Close");
                    }
                    else if(session.equals("Open")){
                        gamelogicResDto.setSession(session);
                    }
                    publiclist.add(gamelogicResDto);
                }
                break;
        }
        return publiclist;


    }


    public List otc(String bidNo, Double point, String session) {
//        List<GamelogicResDto> publiclist = new ArrayList<>();
//        publiclist.removeAll(publiclist);

        Set<GamelogicResDto> publiclist=new HashSet<>();


        int digitCount = bidNo.length();
        GamelogicResDto gamelogicResDto = new GamelogicResDto();
        String gameRateName = "";
        if (digitCount == 1) {
            publiclist.add(new GamelogicResDto(bidNo, point, "Open",gameRateName));
            publiclist.add(new GamelogicResDto(bidNo, point, "Close",gameRateName));
            System.out.println(" publiclist === "+publiclist.toString());
            System.out.println("  publiclist "+publiclist.size());
        }
        if (digitCount == 2) {
            boolean allDigitsSame = true;
            for (char digitChar : bidNo.toCharArray()) {
                int digit = Character.getNumericValue(digitChar);
                char[] digitChars = bidNo.toCharArray();
                char firstDigit = digitChars[0];
                if (digitChar != firstDigit) {
                    allDigitsSame = false;
                    break;
                }
            }
            if (allDigitsSame) {
                publiclist.add(new GamelogicResDto(bidNo, point, "Open",gameRateName));
                System.out.println(" publiclist === "+publiclist.toString());
                System.out.println("  publiclist "+publiclist.size());
            } else {

                StringBuilder reversedStr = new StringBuilder(bidNo).reverse();
                String reversedNumber = String.valueOf(Integer.parseInt(reversedStr.toString()));
                publiclist.add(new GamelogicResDto(bidNo, point, "Open",gameRateName));
                publiclist.add(new GamelogicResDto(reversedNumber, point, "Open",gameRateName));

                System.out.println(" publiclist === "+publiclist.toString());
                System.out.println("  publiclist "+publiclist.size());
            }
        }
        String gameName="";
        if (digitCount == 3) {
            if (bidNo.length()==1) {
                gameName="Single Pana 1 kA";
            } else if ((bidNo.length()==2)) {
                gameName="Jodi 1 KA";
            } else if ((bidNo.length()==3)) {

                char a=bidNo.charAt(0);
                char b=bidNo.charAt(1);
                char c=bidNo.charAt(2);

                if(a==b && a==c){
                    gameName="Triple Pana 1 KA";
                }
                else if(a==b)
                {
                    gameName="Double Pana 1 KA";
                }
                else if(b==c){
                    gameName="Double Pana 1 KA";
                }
                else {
                    gameName="Single Pana 1 KA";
                }
            }
            gamelogicResDto.setGameRateName(gameName);

            publiclist.add(new GamelogicResDto(bidNo, point, "Open",gameRateName));
            publiclist.add(new GamelogicResDto(bidNo, point, "Close",gameRateName));

            System.out.println(" publiclist === "+publiclist.toString());
            System.out.println("  publiclist "+publiclist.size());
        }

        if(bidNo.contains("-")){
            System.out.println(" bid no =="+bidNo);

            String c = "-";
            String r1 = null;
            String r2 = null;
            String st;
            String en;
            if(bidNo.length()>5)
            {
                r1=bidNo;
                r2=bidNo;
                gameRateName="Full Sangam 1 Ka";
            }else if(bidNo.contains(c)&&bidNo.length()==5)
            {
                gameRateName="Half Sangam 1 Ka";
                if(bidNo.charAt(1)=='-'){
                    st=bidNo.substring(0,1);
                    en=bidNo.substring(2,5);
                    r1=st.concat(c).concat(en);
                    r2=en.concat(c).concat(st);
                } else if (bidNo.charAt(3)=='-') {
                    st=bidNo.substring(0,3);
                    en=bidNo.substring(4,5);
                    r1=st.concat(c).concat(en);
                    r2=en.concat(c).concat(st);
                }
            }

            if(bidNo.contains(c)&&bidNo.length()==7)
            {
                gameRateName="Full Sangam 1 Ka";
                String[] parts = bidNo.split("-");
                publiclist.add(new GamelogicResDto(parts[0]+"-"+parts[1],point,"Open",gameRateName));
                publiclist.add(new GamelogicResDto(parts[1]+"-"+parts[0],point,"Open",gameRateName));
            } else if(r1==r2){
                gameRateName="Full Sangam 1 Ka";
                 publiclist.add(new GamelogicResDto(r1, point, "Open",gameRateName));
                 System.out.println(" publiclist === "+publiclist.toString());
                 System.out.println("  publiclist "+publiclist.size());
             }
             else {
                 System.out.println(" r1 ===" + r1);
                 System.out.println(" r1 ===" + r2);
                 publiclist.add(new GamelogicResDto(r1, point, "Open", gameRateName));
                 publiclist.add(new GamelogicResDto(r2, point, "Close", gameRateName));

                 System.out.println(" publiclist === "+publiclist.toString());
                 System.out.println("  publiclist "+publiclist.size());
             }
        }
        System.out.println(" publiclist === "+publiclist.toString());
        System.out.println("  publiclist  "+publiclist.size());

        List list=new ArrayList();
        list.addAll(publiclist);
        System.out.println(" list 121 === "+list.toString());
        System.out.println("  list 121  "+list.size());

        return list;
    }
    public List bktBracket(String bidNo, Double point, String session) {

        System.out.println("  Bid no ="+bidNo);
        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        String[] parts = bidNo.split("-");

        if (parts.length != 2) {
            System.out.println("Invalid input format.");
        }
        int beforeHyphen = Integer.parseInt(parts[0]);
        int afterHyphen = Integer.parseInt(parts[1]);

        Set<Integer> combinations = new HashSet<>();

        int sbeforeHyphen=beforeHyphen;
        int safterHyphen=afterHyphen;

        while(beforeHyphen!=0)
        {
            int i=beforeHyphen%10;
            beforeHyphen=beforeHyphen/10;
            System.out.println(" beforeHyphen = "+beforeHyphen);
            while(afterHyphen!=0)
            {
                int j=afterHyphen%10;
                afterHyphen=afterHyphen/10;
                System.out.println(" afterHyphen = "+afterHyphen);
                combinations.add(i*10+j);
            }
            afterHyphen=safterHyphen;
        }

//        int beforeDigit1 = beforeHyphen / 100;
//        int beforeDigit2 = (beforeHyphen / 10) % 10;
//        int beforeDigit3 = beforeHyphen % 10;
//
//        int afterDigit1 = afterHyphen / 100;
//        int afterDigit2 = (afterHyphen / 10) % 10;
//        int afterDigit3 = afterHyphen % 10;
//
//        combinations.add(beforeDigit1 * 10 + afterDigit1);
//        combinations.add(beforeDigit1 * 10 + afterDigit2);
//        combinations.add(beforeDigit1 * 10 + afterDigit3);
//
//        combinations.add(beforeDigit2 * 10 + afterDigit1);
//        combinations.add(beforeDigit2 * 10 + afterDigit2);
//        combinations.add(beforeDigit2 * 10 + afterDigit3);
//
//        combinations.add(beforeDigit3 * 10 + afterDigit1);
//        combinations.add(beforeDigit3 * 10 + afterDigit2);
//        combinations.add(beforeDigit3 * 10 + afterDigit3);
//
        List<Integer> sortedList = new ArrayList<>(combinations);
        Collections.sort(sortedList);


        for (int i : sortedList) {

            GamelogicResDto gamelogicResDto = new GamelogicResDto();
            gamelogicResDto.setSession(session);
            gamelogicResDto.setPoint(point);
            gamelogicResDto.setGameRateName("Jodi 1 kA");
            String num = String.format("%02d", i);
                gamelogicResDto.setBidNo(num);
                publiclist.add(gamelogicResDto);

        }
        return publiclist;
    }

    public List pannaFamily(String bidNo, Double point, String session) {

        List<GamelogicResDto> publiclist = new ArrayList<>();
        publiclist.removeAll(publiclist);
        int[][] arrayOfArrays = {
                {111, 116, 166, 666},
                {112, 117, 126, 167, 266, 667},
                {113, 118, 136, 168, 366, 668},
                {114, 119, 146, 169, 466, 669},
                {110, 115, 156, 160, 566, 660},
                {122, 127, 177, 226, 267, 677},
                {123, 128, 137, 178, 236, 268, 367, 678},
                {124, 129, 147, 179, 246, 269, 467, 679},
                {120, 125, 157, 170, 256, 260, 567, 670},
                {133, 138, 188, 336, 368, 688},
                {134, 139, 148, 189, 346, 369, 468, 689},
                {130, 135, 158, 180, 356, 360, 568, 680},
                {144, 149, 199, 446, 469, 699},
                {140, 145, 159, 190, 456, 460, 569, 690}
                , {100, 150, 155, 556, 560, 600},
                {222, 227, 277, 777},
                {223, 228, 237, 378, 377, 778},
                {224, 229, 247, 279, 477, 779},
                {220, 225, 257, 270, 577, 770},
                {233, 238, 288, 337, 378, 788},
                {234, 239, 248, 289, 347, 379, 478, 789},
                {230, 235, 258, 280, 357, 370, 578, 780},
                {244, 249, 299, 447, 479, 799},
                {240, 245, 259, 290, 457, 470, 579, 790},
                {200, 250, 255, 257, 270, 700},
                {333, 338, 388, 888},
                {334, 339, 348, 389, 488, 889},
                {330, 335, 358, 380, 588, 880},
                {344, 349, 399, 448, 489, 899},
                {340, 345, 359, 390, 458, 480, 589, 890},
                {300, 350, 355, 558, 580, 800},
                {444, 449, 499, 999},
                {440, 445, 459, 490, 599, 990},
                {400, 450, 455, 559, 590, 900},
                {000, 500, 550, 555}
        };


        int[] foundArray = null; // Store the found array, initially set to null

        for (int[] array : arrayOfArrays) {
//            System.out.println("ARRAY ="+ Arrays.stream(array).toArray().length);

//            for (int i : array) {
//                System.out.println(i);
//            }
            if (containsValue(array, Integer.parseInt(bidNo))) {
                foundArray = array;
                break;
            }
        }
//        System.out.println(" foundArray size= "+foundArray.length);
        if (foundArray != null) {


            for (int num : foundArray) {

                System.out.println(num);
                GamelogicResDto gamelogicResDto = new GamelogicResDto();
                gamelogicResDto.setSession(session);
                String gameName = "";
                String snum=String.valueOf(num);

                String num1 = String.format("%03d", num);
                if (snum.length()<= 3) {
//                    if (bidNo.length() == 1) {
//                        gameName = "Single Pana 1 kA";
//                    } else if ((bidNo.length() == 2)) {
//                        gameName = "Jodi 1 KA";
//                    } else if ((bidNo.length() == 3)) {
                    char a,b,c;

                    if(snum.compareTo("0")==0)
                    {
                       a='0';
                       b='0';
                       c='0';
                    }else {
                        a = snum.charAt(0);
                        b = snum.charAt(1);
                        c = snum.charAt(2);
                    }
                        if ( a == b && a == c) {
                            gameName = "Triple Pana 1 KA";
                        } else if (a == b) {
                            gameName = "Double Pana 1 KA";
                        } else if (b == c) {
                            gameName = "Double Pana 1 KA";
                        } else {
                            gameName = "Single Pana 1 KA";
                        }
                    }
                    gamelogicResDto.setPoint(point);
                    gamelogicResDto.setBidNo(num1);
                    gamelogicResDto.setGameRateName(gameName);
                    publiclist.add(gamelogicResDto);
                }

            }
//        }
        return publiclist;
    }



    public static boolean containsValue(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }


    private  void num(String i) {
        String number ="23";


        if (number.length()==1) {
            System.out.println("Single 1 KA");
        } else if ((number.length()==2)) {
            System.out.println("Jodi 1 KA");
        } else if ((number.length()==3)) {

            char a=number.charAt(0);
            char b=number.charAt(1);
            char c=number.charAt(2);

            if(a==b && a==c){
                System.out.println("Triple Pana 1 KA");
            }
            else if(a==b)
            {
                System.out.println("Double Pana 1 KA");
            }
            else if(b==c){
                System.out.println("Double Pana 1 KA");
            }
            else {
                System.out.println("Single Pana 1 KA");
            }
        }
    }

}














//if (foundArray != null) {
//
//        for (int num : foundArray) {
//        System.out.println(num);
//        GamelogicResDto gamelogicResDto = new GamelogicResDto();
//        gamelogicResDto.setSession(session);
//        String gameName = "";
//        String snum=String.valueOf(num);
//        if (snum.length()<= 3) {
//        if (bidNo.length() == 1) {
//        gameName = "Single Pana 1 kA";
//        } else if ((bidNo.length() == 2)) {
//        gameName = "Jodi 1 KA";
//        } else if ((bidNo.length() == 3)) {
//
//        char a = bidNo.charAt(0);
//        char b = bidNo.charAt(1);
//        char c = bidNo.charAt(2);
//
//        if (a == b && a == c) {
//        gameName = "Triple Pana 1 KA";
//        } else if (a == b) {
//        gameName = "Double Pana 1 KA";
//        } else if (b == c) {
//        gameName = "Double Pana 1 KA";
//        } else {
//        gameName = "Single Pana 1 KA";
//        }
//        }
//        gamelogicResDto.setPoint(point);
//        gamelogicResDto.setBidNo(String.valueOf(num));
//        gamelogicResDto.setGameRateName(gameName);
//        publiclist.add(gamelogicResDto);
//        }
//        }
//        }


