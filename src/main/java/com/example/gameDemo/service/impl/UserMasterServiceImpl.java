package com.example.gameDemo.service.impl;

import com.example.gameDemo.configuration.RandomNumberGenerator;
import com.example.gameDemo.model.ERole;
import com.example.gameDemo.model.Role;
//import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.payload.req.SignupRequest;
import com.example.gameDemo.payload.req.UserTokenReqDto;
import com.example.gameDemo.payload.res.OtpResDto;
import com.example.gameDemo.payload.res.PhoneNoResDto;
import com.example.gameDemo.payload.res.UserDetailsResponse;
import com.example.gameDemo.repository.RoleRepository;
import com.example.gameDemo.repository.UserRepository;
import com.example.gameDemo.service.UserMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserMasterServiceImpl implements UserMasterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List getAllUsers() {
        List<UserMaster> list=new ArrayList<>();
        list= userRepository.findAllByOrderByIdDesc();
//        list.remove(0);
        System.out.println(list.size());
       return list;
    }

    @Override
    public List<UserMaster> getAllActiveUser() {
        List<UserMaster> userMasterList=userRepository.findByStatus("Active");
        return userMasterList;
    }

    @Override
    public List<Role> getRoleByUserId() {
        List<Role> role=roleRepository.findAll();
        return role;
    }

    @Override
    public OtpResDto checkUserMobileNumber(String mobNo) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        OtpResDto otpResDto=new OtpResDto();
        Integer id = userRepository.checkUserMobileNumber(mobNo);
        System.out.println(id);
        if (id != null) {
            otpResDto.setFlag(false);
        } else {

            Integer otp = RandomNumberGenerator.getNumber();
            String msg = "" + "\n" + otp;
            System.out.println(otp);
            otpResDto.setMobNo(mobNo);
            otpResDto.setOtp(otp);
            otpResDto.setFlag(true);
        }
        return otpResDto;
    }

    @Override
    public UserMaster editAdminSetting(Long id) {
        UserMaster userMaster=new UserMaster();
        try {
            Optional<UserMaster> userMaster1=userRepository.findById(id);
            userMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, userMaster));
            return userMaster;
        }
        catch (Exception e) {
            e.printStackTrace();
            return userMaster;
        }
    }

    @Override
    public Boolean UpdateUserMaster(SignupRequest signupRequest) {
        UserMaster userMaster=userRepository.getByUserIdWise(signupRequest.getId());
        BeanUtils.copyProperties(signupRequest, userMaster);
        userMaster.setPassword(encoder.encode(signupRequest.getPassword()));
        userMaster.setSPassword(signupRequest.getPassword());
        userMaster.setAddress(signupRequest.getAddress());
        userMaster.setWhatsAppNo(signupRequest.getWhatsAppNo());
//        userMaster.setDate(signupRequest.getDate());
        String strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        switch (strRoles) {
            case "admin":
                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;
            case "agent":
                userRole = roleRepository.findByName(ERole.ROLE_AGENT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;
            case "user":
                userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;
        }
        userMaster.setRoles(roles);
        UserMaster userMaster1=userRepository.save(userMaster);
        System.out.println(userMaster1.getCanPlay());
        try {
            if(signupRequest.getCanPlay().equals("Yes") && userMaster1.getCanPlayFlag().equals(false)) {
                System.out.println("HI Yes");
                userMaster1.setCanPlay(signupRequest.getCanPlay());
                Double walletPoint = userMaster1.getWalletPoints();
                Double w = walletPoint + 5;
                System.out.println("w"+w);
                userMaster1.setWalletPoints(w);
                userMaster1.setCanPlayFlag(true);
                UserMaster um=userRepository.save(userMaster1);
                System.out.println("um"+um.getUsername());

            }
            else if(signupRequest.getCanPlay()!=null && userMaster.getCanPlayFlag().equals(true)) {
                System.out.println("Hi No");
                userMaster1.setCanPlay(signupRequest.getCanPlay());
                UserMaster um =userRepository.save(userMaster1);
                System.out.println(" UserMaster um"+um.getEmail()+ "stattus ..."+um.getCanPlay());
            }
                System.out.println(userMaster.getDate());
                return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PhoneNoResDto getPhoneNoUserIdWise(Long id) {
        PhoneNoResDto phoneNoResDto=userRepository.getPhoneNoUserIdWise(id);
        return phoneNoResDto;
    }

    @Override
    public Double getWalletPointsUserIdWise(Long id) {
        Double walletPoints=userRepository.findWalletBalance(id);
        return walletPoints;
    }

    @Override
    public Integer updateUserToken(UserTokenReqDto userTokenReqDto) {
        return userRepository.updateTokenByUSerId(userTokenReqDto.getId(), userTokenReqDto.getToken());

    }

    @Override
    public String getCanPlayUserIdWise(Long id) {
        String canPlay=userRepository.getCanPlayUserIdWise(id);
        return canPlay;
    }

    @Override
    public Integer getUserCount() {
        Integer count=userRepository.getUserCount(new Date());
        return count;
    }

    @Override
    public Boolean updateUserPoint(Long id, String canPlay) {
        Boolean flag=false;

        UserMaster userMaster=userRepository.getByUserIdWise(id);

        userMaster.setCanPlay(canPlay);
        Double walletPoint=userMaster.getWalletPoints();

       Double w= walletPoint+5;
       userMaster.setWalletPoints(w);

       userRepository.save(userMaster);
       return true;
    }

    @Override
    public UserDetailsResponse userDetails(Long id) {
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        UserMaster master = this.userRepository.findById(id).get();

        UserMaster agent = this.userRepository.findById(master.getAgentId()).get();
        userDetailsResponse.setAgentId(agent.getId());
        userDetailsResponse.setAgentFullName(agent.getFullName());

        userDetailsResponse.setFullName(master.getFullName());
        userDetailsResponse.setId(master.getId());
        userDetailsResponse.setEmail(master.getEmail());
        userDetailsResponse.setMobileNo(master.getMobileNo());
        userDetailsResponse.setGooglePayNo(master.getGooglePayNo());
        userDetailsResponse.setPhonePayNo(master.getPhonePayNo());
        userDetailsResponse.setPaytmNo(master.getPaytmNo());
        userDetailsResponse.setWalletPoints(master.getWalletPoints());
        userDetailsResponse.setStatus(master.getStatus());
        userDetailsResponse.setPassword(master.getPassword());
        userDetailsResponse.setBankName(master.getBankName());
        userDetailsResponse.setBankAccountNo(master.getBankAccountNo());
        userDetailsResponse.setIfscCode(master.getIfscCode());
        userDetailsResponse.setAccountHolderName(master.getAccountHolderName());
        userDetailsResponse.setAddress(master.getAddress());
        userDetailsResponse.setCity(master.getCity());
        userDetailsResponse.setPinCode(master.getPinCode());
        return userDetailsResponse;
    }

    @Override
    public Map<String, String> getAgentIdWiseWhatsAppData(Long id) {
        UserMaster userMaster=new UserMaster();
        userMaster=userRepository.findById(id).orElseThrow(RuntimeException::new);
        Map<String,String> response =new HashMap<>();
        response.put("whatsAppNo",userMaster.getWhatsAppNo());
        return response;
    }
}


