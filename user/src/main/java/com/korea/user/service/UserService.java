package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository respository;

	public List<UserDTO> create(UserEntity entity){
		respository.save(entity);
		return respository.findAll().stream()
					.map(UserDTO::new).collect(Collectors.toList());
	}
	
	public List<UserDTO> getfindAll(){
		return respository.findAll().stream()
				.map(UserDTO::new).collect(Collectors.toList());
	}
	
	public UserDTO getUserEmail(String email) {
		UserEntity entity = respository.findByEmail(email);
		return new UserDTO(entity);
	}
	
	public List<UserDTO> update(UserEntity entity){
		Optional<UserEntity> user = respository.findById(entity.getId());
		user.ifPresent(userEntity -> {
			userEntity.setName(entity.getName());
			userEntity.setEmail(entity.getEmail());
			              
			respository.save(userEntity);
		});
		return getfindAll();
		
	}
	
	public boolean deleteUser(int id){
		Optional<UserEntity> user = respository.findById(id);
		if(user.isPresent()) {
			respository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
