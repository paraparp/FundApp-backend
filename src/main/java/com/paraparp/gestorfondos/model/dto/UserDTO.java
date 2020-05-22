package com.paraparp.gestorfondos.model.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class UserDTO {
	
	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;	
	private LocalDate creationDate;
	private String password;
	private boolean google;
	private boolean enabled;

}
