package com.paraparp.gestorfondos.model.dto.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.paraparp.gestorfondos.model.dto.GetUserDTO;
import com.paraparp.gestorfondos.model.entity.UserEntity;
import com.paraparp.gestorfondos.model.entity.UserRole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {

	public GetUserDTO convertUserEntityToGetUserDto(UserEntity user) {

		return GetUserDTO.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.avatar(user.getAvatar())
				.roles(user.getRoles()
						.stream().map(UserRole::name).collect(Collectors.toSet())).build();
	}



}
