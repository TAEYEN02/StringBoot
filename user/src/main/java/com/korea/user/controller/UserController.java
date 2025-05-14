package com.korea.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.DeleteExchange;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserDTO dto){
		UserEntity entity = UserDTO.toEntity(dto);
		List<UserDTO> user = service.create(entity);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<?> getfindAll(){
		List<UserDTO> user = service.getfindAll();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getUserEmail(@PathVariable String email){
		UserDTO user = service.getUserEmail(email);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserDTO dto){
		UserEntity entity = UserDTO.toEntity(dto);	
		List<UserDTO> user = service.update(entity);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		boolean user = service.deleteUser(id);
		if(user) {
			return ResponseEntity.ok("User delete service ok");
		}else {
			return ResponseEntity.status(400).body("User Not Find Id : "+id);
		}
	}
}
