package com.korea.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.mybatis.domain.User;
import com.korea.mybatis.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	public List<User> getAllUsers(){
		return userMapper.findAll();
	}
	
	public void createUser(User user) {
		userMapper.insert(user);
	}
}
