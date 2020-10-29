package com.paraparp.gestorfondos.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {

	private String username;
	private String email;
	private String avatar;
	private String password;
	private String password2;
	
	
	
}
