package com.korea.user.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<List<UserDTO>> createUser(@RequestBody UserDTO dto){
			UserEntity entity = UserDTO.toEntity(dto);
			List<UserDTO> dtos = userService.create(entity);
			return ResponseEntity.ok(dtos);	
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
	    List<UserDTO> users = userService.getAllUsers();
	    return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
	    UserDTO user = userService.getUserByEmail(email);
	    return ResponseEntity.ok(user);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserDTO dto) {
	    UserEntity entity = UserDTO.toEntity(dto);
	    List<UserDTO> updatedUser = userService.updateUser(entity);

	    return ResponseEntity.ok(updatedUser);
	    }
}
