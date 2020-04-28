package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.UserDTO;
import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.repository.IUserRepository;
import com.paraparp.gestorfondos.service.IUserService;


@Service
public class UserService implements IUserService, UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private IUserRepository userRepository;


	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		
		List<UserDTO> listUsers = new ArrayList<UserDTO>();
		List<User> usersBack = this.userRepository.findAll();

		usersBack.forEach(user -> listUsers.add(this.modelMapper.map(user, UserDTO.class)));
		
		return listUsers;
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		return null;
	}

	@Override
	public UserDTO save(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			logger.error("Error en el login: No existe el usuario: "+ username);
			throw new UsernameNotFoundException("Error en el login: No existe el usuario: "+ username);
		}
	
		

		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		user.getLastName();
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

}
