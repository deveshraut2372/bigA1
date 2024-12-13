package com.example.gameDemo.controller;


import com.example.gameDemo.model.*;
import com.example.gameDemo.payload.req.*;
import com.example.gameDemo.payload.res.*;
import com.example.gameDemo.repository.GameRateMasterDao;
import com.example.gameDemo.repository.GameRateUserDao;
import com.example.gameDemo.repository.RoleRepository;
import com.example.gameDemo.repository.UserRepository;
import com.example.gameDemo.security.exception.TokenRefreshException;
import com.example.gameDemo.security.jwt.JwtUtils;
import com.example.gameDemo.service.RefreshTokenService;
import com.example.gameDemo.service.UserMasterService;
import com.example.gameDemo.service.impl.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserMasterService userMasterService;

    @Autowired
    UserRepository userMasterDao;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private GameRateMasterDao gameRateMasterDao;

    @Autowired
    private GameRateUserDao gameRateUserDao;

        @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        System.out.println("  loginRequest ="+loginRequest.toString());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMobileNo(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String role = roles.get(0);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        JwtResponse jwtResponse1=new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getMobileNo(), role,"Login Succesfully ");

        UserMaster userMaster=new UserMaster();
        userMaster=userMasterDao.findById(userDetails.getId()).get();


            JwtResponse jwtResponse2=new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                    userDetails.getMobileNo(), role,"Login Succesfully ", userMaster.getUsername(),userMaster.getEmail(),userMaster.getPassword(),userMaster.getStatus(),userMaster.getWalletPoints());


        return ResponseEntity.ok(jwtResponse2);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userMasterDao.existsByMobileNo(signUpRequest.getMobileNo())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: mobileNo is already taken!",false,HttpStatus.BAD_REQUEST.value()));
        }

        UserMaster user = new UserMaster();

        BeanUtils.copyProperties(signUpRequest, user);
        user.setEmail(signUpRequest.getEmail());

        user.setStatus("Active");
        user.setCanPlayFlag(false);
        user.setCanPlay("No");
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setSPassword(signUpRequest.getPassword());
        user.setCommision(1);
        String strRoles = signUpRequest.getRole();
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
        user.setRoles(roles);
        user.setRegDate(new Date());
        UserMaster user1 = userMasterDao.save(user);

//        UserMaster userMaster=userMasterDao.getByUserIdWise(signUpRequest.getId());
        List<GameRateMaster> gameRateMaster= (List<GameRateMaster>) gameRateMasterDao.findAll();

        for(GameRateMaster gameRateMaster1:gameRateMaster) {
            System.out.println("hiiii");
            GameRateUserWiseMaster gameRateUserWiseMaster = new GameRateUserWiseMaster();
            gameRateUserWiseMaster.setGameRate(gameRateMaster1.getGameRate());
            gameRateUserWiseMaster.setGameName(gameRateMaster1.getGameName());
            gameRateUserWiseMaster.setStatus(gameRateMaster1.getStatus());

            gameRateUserWiseMaster.setUserMaster(user1);
            gameRateUserWiseMaster.setGameRateMaster(gameRateMaster1);
            gameRateUserDao.save(gameRateUserWiseMaster);

            }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!",true,HttpStatus.OK.value()));
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getMobileNo());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }


    @GetMapping(value = "/getAllUser")
    public ResponseEntity getAllUser() {
        System.out.println("hiiiiiiiiiiii");
        List list = userMasterService.getAllUsers();
        if (list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(list, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getRoleByUserId")

    public ResponseEntity getRoleByUserId() {

        List<Role> role = userMasterService.getRoleByUserId();

            return new ResponseEntity(role, HttpStatus.OK);

    }

    @GetMapping(value = "/checkMobNo/{mobNo}")
    public ResponseEntity checkMobile(@PathVariable String mobNo) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        OtpResDto flag = userMasterService.checkUserMobileNumber(mobNo);
        return new ResponseEntity(flag,HttpStatus.OK);
    }

    @GetMapping(value = "/getByUserIdWise/{id}")
    public ResponseEntity editAdminSetting(@PathVariable Long id)
    {
        UserMaster userMaster = userMasterService.editAdminSetting(id);
        if(userMaster!=null)
        {
            return new ResponseEntity(userMaster, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(userMaster,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/UpdateUserMaster")
    public ResponseEntity UpdateUserMaster(@RequestBody SignupRequest signupRequest) {
        MessageResponse mainResDto = new MessageResponse();
        Boolean flag = userMasterService.UpdateUserMaster(signupRequest);

        if (flag) {
            mainResDto.setMessage(" Updated ");
            mainResDto.setResponseCode(201);
            mainResDto.setFlag(flag);
            return new ResponseEntity(mainResDto, HttpStatus.CREATED);
        } else {
            mainResDto.setMessage("  not Updated ");
            mainResDto.setFlag(flag);
            return new ResponseEntity(mainResDto, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/getPhoneNoUserIdWise/{id}")
    public ResponseEntity getPhoneNoUserIdWise(@PathVariable Long id) {
        PhoneNoResDto  phoneNoResDto = userMasterService.getPhoneNoUserIdWise(id);
        return new ResponseEntity(phoneNoResDto,HttpStatus.OK);
    }
    @GetMapping(value = "/getWalletPointsUserIdWise/{id}")
    public ResponseEntity getWalletPointsUserIdWise(@PathVariable Long id) {
       Double walletPoints  = userMasterService.getWalletPointsUserIdWise(id);
        return new ResponseEntity(walletPoints,HttpStatus.OK);
    }

    @PostMapping(value = "/updateUserToken")
    public ResponseEntity updateToken(@RequestBody UserTokenReqDto userTokenReqDto){

        Integer no = userMasterService.updateUserToken(userTokenReqDto);
        if(no>0)
        {
            return new ResponseEntity(true,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getCanPlayUserIdWise/{id}")
    public ResponseEntity getCanPlayUserIdWise(@PathVariable Long id) {
        String  canPlay  = userMasterService.getCanPlayUserIdWise(id);
        return new ResponseEntity(canPlay,HttpStatus.OK);
    }

    @GetMapping(value = "/getUserCount")
    public ResponseEntity getUserCount() {
        Integer no = userMasterService.getUserCount();
            return new ResponseEntity(no, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateUserPoint/{id}/{canPlay}")
    public ResponseEntity updateUserPoint(@PathVariable Long id,@PathVariable String canPlay) {
        Boolean flag = userMasterService.updateUserPoint(id,canPlay);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}




