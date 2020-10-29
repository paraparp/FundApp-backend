package com.paraparp.gestorfondos.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

//	private final AuthenticationManager authenticationManager;
//	private final UserDTOConverter converter;
//
//	@PostMapping("/auth/login")
//	public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
//
//		Authentication auth = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(auth);
//
//		UserEntity user = (UserEntity) auth.getPrincipal();
//
//		String jwtToken = jwtTokenProvider.generateToken(auth);
//
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(convertUserEntityAndTokenToJwtUserResponse(user, jwtToken));
//
//	}
//
//	public GetUserDTO me(@AuthenticationPrincipal UserEntity user) {
//		return converter.convertUserEntityToGetUserDto(user);
//	}
//
//	private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(UserEntity user, String jwtToken) {
//		return JwtUserResponse.jwtUserResponseBuilder().email(user.getEmail()).avatar(user.getAvatar())
//				.username(user.getUsername())
//				.roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet())).token(jwtToken)
//				.build();
//	}

}
