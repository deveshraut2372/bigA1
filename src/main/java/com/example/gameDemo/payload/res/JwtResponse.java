package com.example.gameDemo.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    //    private String firstName;
//    private String middleName;
//    private String lastName;
    private String mobileNo;

    private String roles;

    private String message;

    //
    private String username;

    private String email;

    private String password;

    private String status;

    private Double walletPoints=0.0;

    private Long agentId;

    private String fullName;


//	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, String userMobNo, String roles) {
//		this.token = accessToken;
//		this.refreshToken = refreshToken;
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.roles = roles;
//	}


    public JwtResponse(String token,String refreshToken, Long id, String mobileNo, String roles, String message) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.mobileNo = mobileNo;
        this.roles = roles;
        this.message = message;
    }

    public JwtResponse(String token, String refreshToken, Long id, String mobileNo, String roles, String message, String username, String email, String password, String status, Double walletPoints, Long agentId, String fullName) {
        this.token = token;

        this.refreshToken = refreshToken;
        this.id = id;
        this.mobileNo = mobileNo;
        this.roles = roles;
        this.message = message;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.walletPoints = walletPoints;
        this.agentId = agentId;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", id=" + id +
                ", mobileNo='" + mobileNo + '\'' +
                ", roles='" + roles + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    //    public JwtResponse(String accessToken, String refreshToken, Long id, String mobileNo, String roles, String message) {
//        this.token = accessToken;
//        this.refreshToken = refreshToken;
//        this.id = id;
////        this.firstName = username;
//        this.mobileNo = mobileNo;
//        this.roles = roles;
//        this.message=message;
//    }




//	public String getUserMobNo() {
//		return userMobNo;
//	}
//
//	public void setUserMobNo(String userMobNo) {
//		this.userMobNo = userMobNo;
//	}

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String email) {
        this.mobileNo = email;
    }

//    public String getFirstName() {
//        return firstName;
//    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//	public String getMiddleName() {
//		return middleName;
//	}

//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}


    public String getRoles() {
        return roles;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
