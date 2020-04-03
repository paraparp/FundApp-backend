package com.paraparp.gestorfondos.service;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.User;

@Service
public interface IUserService {
	public User findByUsername(String username);

}
