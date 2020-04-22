package com.paraparp.gestorfondos.service.imp;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.entity.User;

@Service
public interface IUserService {
	public User findByUsername(String username);

}
