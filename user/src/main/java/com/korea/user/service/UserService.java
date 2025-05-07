package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	
	public List<UserDTO> create(UserEntity entity){	
		repository.save(entity);
		return repository.findAll().stream()
				.map(UserDTO::new)
				.collect(Collectors.toList());
	}

	public List<UserDTO> getAllUsers() {
		 return repository.findAll().stream()
		            .map(UserDTO::new)  // UserEntity -> UserDTO로 변환
		            .collect(Collectors.toList());
	}

	public UserDTO getUserByEmail(String email) {
		UserEntity entity = repository.findByEmail(email);
	    return new UserDTO(entity);
	}
	
	public List<UserDTO> updateUser(UserEntity entity) {
	    Optional<UserEntity> userEntityOptional = repository.findById(entity.getId());

	    userEntityOptional.ifPresent(userEntity -> {
	        // 이름과 이메일을 업데이트
	        userEntity.setName(entity.getName());
	        userEntity.setEmail(entity.getEmail());

	        // 업데이트된 사용자 정보 저장
	        repository.save(userEntity);
	    });

	    return getAllUsers();
	}
	

	

}
