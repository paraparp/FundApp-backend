package com.paraparp.gestorfondos.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paraparp.gestorfondos.errors.exceptions.NewUserWithDifferentPasswordsException;
import com.paraparp.gestorfondos.model.dto.CreateUserDTO;
import com.paraparp.gestorfondos.model.entity.UserEntity;
import com.paraparp.gestorfondos.model.entity.UserRole;
import com.paraparp.gestorfondos.repository.UserEntityRepository;
import com.paraparp.gestorfondos.service.base.BaseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository>  {

	private final PasswordEncoder passwordEncoder;

	public Optional<UserEntity> findUserByUsername(String username) {
		return this.repository.findByUsername(username);
	}
	
	
	public List<UserEntity> findAll() {
		return this.repository.findAll();
	}

	public UserEntity newUser(CreateUserDTO newUser) {

//		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//		userEntity.setRoles(Stream.of(UserRole.USER).collect(Collectors.toSet()));
//		//userEntity.setRoles(Set.of(UserRole.USER)); //Java 9
//		return save(userEntity);

		if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
			UserEntity userEntity = null;
			try {
				 userEntity = UserEntity.builder()
						.username(newUser.getUsername())
						.password(passwordEncoder.encode(newUser.getPassword()))
						.avatar(newUser.getAvatar()).email(newUser.getEmail())
						.roles(Stream.of(UserRole.USER)
								.collect(Collectors.toSet())).build();
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords don't match");
			}
		
			try {
				
		
				return save(userEntity);
			} catch (DataIntegrityViolationException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
			}
		} 
		else {
			throw new NewUserWithDifferentPasswordsException();
		}
	}

}
