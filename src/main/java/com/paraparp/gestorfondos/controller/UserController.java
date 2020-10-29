package com.paraparp.gestorfondos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.paraparp.gestorfondos.errors.exceptions.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.dto.CreateUserDTO;
import com.paraparp.gestorfondos.model.dto.GetUserDTO;
import com.paraparp.gestorfondos.model.dto.converter.UserDTOConverter;
import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.model.entity.UserEntity;
import com.paraparp.gestorfondos.service.imp.UserEntityService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fundapp/users")
@RequiredArgsConstructor
public class UserController {

//	final private IUserRepository userRepository;
	final private UserEntityService userEntityService;
//	final private StorageService storageService;
	final private UserDTOConverter userDTOConverter;

	// @Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("/register")
	public ResponseEntity<GetUserDTO> createUser(@Valid @RequestBody CreateUserDTO newUser) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDTOConverter.convertUserEntityToGetUserDto(userEntityService.newUser(newUser)));
	}

	@GetMapping("/me")
	public GetUserDTO me(@AuthenticationPrincipal UserEntity user) {
		return userDTOConverter.convertUserEntityToGetUserDto(user);
	}

//	@Secured("ROLE_ADMIN" )
	@GetMapping("")
	public List<UserEntity> getAllUsers() {
		return userEntityService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "id") Long userId) {
		UserEntity user = userEntityService.findById(userId)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok().body(user);
	}
//
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
//	@PutMapping(value = "/avatar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<User> updateAvatarUser(@PathVariable Long id, @RequestPart("file") MultipartFile file)
//			throws ResourceNotFoundException {
//
//		String urlAvatar = null;
//
//		if (!file.isEmpty()) {
//			String imagen = storageService.store(file);
//			urlAvatar = MvcUriComponentsBuilder
//					// El segundo argumento es necesario solo cuando queremos obtener la imagen
//					// En este caso tan solo necesitamos obtener la URL
//					.fromMethodName(FileController.class, "serveFile", imagen, null).build().toUriString();
//		}
//
//		User user = this.checkUser(id);
//		;
//		user.setAvatar(urlAvatar);
//		final User updatedUser = userRepository.save(user);
//		return ResponseEntity.ok(updatedUser);
//	}
//
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
//	@PutMapping("/{id}")
//	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails)
//			throws ResourceNotFoundException {
//		User user = this.checkUser(id);
//
//		user.setEmail(userDetails.getEmail());
//		user.setLastName(userDetails.getLastName());
//		user.setFirstName(userDetails.getFirstName());
//
//		final User updatedUser = userRepository.save(user);
//		return ResponseEntity.ok(updatedUser);
//	}
//
//	@Secured("ROLE_USER")
//	@DeleteMapping("/{id}")
//	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
//		User user = this.checkUser(userId);
//
//		userRepository.delete(user);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;
//	}

	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * 
	 * @param userId
	 * @return
	 * @throws ResourceNotFoundException
	 */
//	private User checkUser(Long userId) throws ResourceNotFoundException {
//		return userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
//
//	}
}