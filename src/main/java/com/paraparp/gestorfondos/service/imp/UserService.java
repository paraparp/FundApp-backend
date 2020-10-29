package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.UserDTO;
import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.repository.IUserRepository;
import com.paraparp.gestorfondos.service.IUserService;

import lombok.RequiredArgsConstructor;

//@Service
//@RequiredArgsConstructor
public class UserService 
//implements IUserService 
{

//	private Logger logger = LoggerFactory.getLogger(UserService.class);
//
//	ModelMapper modelMapper = new ModelMapper();
//
//	private IUserRepository userRepository;
//	private IUserService serviceRepository;
//
//	@Override
//	@Transactional(readOnly = true)
//	public List<UserDTO> findAll() {
//
//		List<UserDTO> listUsers = new ArrayList<>();
//		List<User> usersBack = this.userRepository.findAll();
//
//		usersBack.forEach(user -> listUsers.add(this.modelMapper.map(user, UserDTO.class)));
//
//		return listUsers;
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public UserDTO findById(Long id) {
//
//		return null;
//	}
//
//	@Override
//	public UserDTO save(UserDTO user) {
//		return userRepository.save(user);
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		userRepository.deleteById(id);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public User findByUsername(String username) {
//		return userRepository.findByUsername(username);
//	}
//
//	@Override
//	public User save(@Valid User user) {
//		return serviceRepository.save(user);
//	}

}
