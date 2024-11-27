package com.example.gameDemo.payload.res;

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

//	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, String userMobNo, String roles) {
//		this.token = accessToken;
//		this.refreshToken = refreshToken;
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.roles = roles;
//	}


    public JwtResponse(String accessToken, String refreshToken, Long id, String mobileNo, String roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
//        this.firstName = username;
        this.mobileNo = mobileNo;
        this.roles = roles;
    }




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
