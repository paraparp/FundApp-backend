package com.paraparp.gestorfondos.model.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;


@Data
public class UserDTO {
	
	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;	
	private Date creationDate;
	private String password;
	private boolean google;
	private boolean enabled;

}
