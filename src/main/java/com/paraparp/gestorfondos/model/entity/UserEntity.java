package com.paraparp.gestorfondos.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class UserEntity {
	
	private String username;
	private String password;
	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

}