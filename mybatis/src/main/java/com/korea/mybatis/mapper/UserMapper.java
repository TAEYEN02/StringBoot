package com.korea.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.korea.mybatis.domain.User;

@Mapper //자동으로 bean으로 등록된다.
public interface UserMapper {

	List<User> findAll(); //전체 유저 조회
	
	User findById(Long id); //id를 통한 유저 한건 조회
	
	void insert(User user); //유저 추가하기
	void update(User user); 
	void delete(Long id);
}
