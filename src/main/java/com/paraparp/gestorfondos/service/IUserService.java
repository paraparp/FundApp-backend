package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.UserDTO;
import com.paraparp.gestorfondos.model.entity.User;

@Service
public interface IUserService {
	
	public UserDTO findById(Long id);

	public UserDTO save(UserDTO user);

	public void deleteById(Long id);

	public List<UserDTO> findAll();

	public User findByUsername(String username);

}
