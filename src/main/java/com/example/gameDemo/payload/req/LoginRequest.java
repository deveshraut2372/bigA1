package com.example.gameDemo.payload.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class LoginRequest {
	@NotBlank
	private String mobileNo;

	@NotBlank
			private String password;


}
